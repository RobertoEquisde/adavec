async function cargarUnidad(idUnidad) {
    try {
        const response = await axios.get(`http://127.0.0.1:8080/api/unidades/${idUnidad}`);
        const unidad = response.data;

        // 🔽 Llena los campos según tus inputs/selects
        document.querySelector("#modeloInput").value = unidad.modelo;
        // document.querySelector("#origenInput").value = unidad.origen || "";
        document.querySelector(".serie-badge").innerText = "serie:" + unidad.id;
        document.querySelector("#modeloInput").value = unidad.modelo || "";
        document.querySelector("#placasInput").value = unidad.placas || "";
        document.querySelector("#tipoUnidadInput").value = unidad.tipoUnidad || "";
        document.querySelector("#anioInput").value = unidad.anio || "";
        document.querySelector("#comentariosInput").value = unidad.comentarios || "";
        document.querySelector("#reportadoAInput").value = unidad.reportadoA || "";
        // document.querySelector("#proveedorSelect").value = unidad.proveedor?.nombre || "";
        document.querySelector(".serie-badge").innerText = "serie:" + unidad.id;

        // Aquí puedes seguir agregando campos relacionados

    } catch (error) {
        console.error("❌ Error al cargar unidad:", error);
        alert("No se pudo cargar la información de la unidad.");
    }
}
async function cargarUbicaciones() {
    try {
        const response = await axios.get('http://127.0.0.1:8080/api/ubicaciones');
        const ubicaciones = response.data;

        const origenSelect = document.getElementById('origenSelect');
        const destinoSelect = document.getElementById('destinoSelect');

        // Limpiar selects
        origenSelect.innerHTML = '<option value="">Seleccione origen</option>';
        destinoSelect.innerHTML = '<option value="">Seleccione destino</option>';

        ubicaciones.forEach(ubicacion => {
            const optionOrigen = document.createElement('option');
            optionOrigen.value = ubicacion.id;
            optionOrigen.text = ubicacion.nombre;
            origenSelect.appendChild(optionOrigen);

            const optionDestino = document.createElement('option');
            optionDestino.value = ubicacion.id;
            optionDestino.text = ubicacion.nombre;
            destinoSelect.appendChild(optionDestino);
        });

        // Validación: evitar que seleccionen la misma ubicación
        origenSelect.addEventListener('change', () => {
            const origenSeleccionado = origenSelect.value;
            [...destinoSelect.options].forEach(opt => {
                opt.disabled = opt.value === origenSeleccionado && opt.value !== "";
            });
        });

        destinoSelect.addEventListener('change', () => {
            const destinoSeleccionado = destinoSelect.value;
            [...origenSelect.options].forEach(opt => {
                opt.disabled = opt.value === destinoSeleccionado && opt.value !== "";
            });
        });

    } catch (error) {
        console.error('❌ Error al cargar ubicaciones:', error);
    }
}

const coloresPorTipo = {
    "TARIFA ÚNICA": "primary",
    "COMBUSTIBLE": "info",
    "REPARACIONES": "warning text-dark",
    "SEGURO": "success",
    "PENSIÓN": "secondary",
    "LODERAS": "dark",
    "OTROS": "danger"
};
async function cargarTrasladosPorUnidad(idUnidad) {
    try {
        const response = await axios.get(`http://127.0.0.1:8080/api/unidades/${idUnidad}/traslados`);
        const traslados = response.data;

        const tbody = document.getElementById("tabla-cobros-body");
        tbody.innerHTML = "";

        for (const traslado of traslados) {
            const cobros = traslado.cobros || [];

            for (const cobro of cobros) {
                const fecha = cobro.fecha || "-";

                const tipos = [
                    { tipo: "TARIFA ÚNICA", valor: cobro.tarifaUnica },
                    { tipo: "COMBUSTIBLE", valor: cobro.combustible },
                    { tipo: "REPARACIONES", valor: cobro.reparaciones },
                    { tipo: "SEGURO", valor: cobro.seguro },
                    { tipo: "PENSIÓN", valor: cobro.pension },
                    { tipo: "LODERAS", valor: cobro.loderas },
                    { tipo: "OTROS", valor: cobro.otrosGastos }
                ];

                const coloresPorTipo = {
                    "TARIFA ÚNICA": "primary",
                    "COMBUSTIBLE": "info",
                    "REPARACIONES": "warning text-dark",
                    "SEGURO": "success",
                    "PENSIÓN": "secondary",
                    "LODERAS": "dark",
                    "OTROS": "danger"
                };

                tipos.forEach(t => {
                    if (t.valor && t.valor > 0) {
                        const colorClase = coloresPorTipo[t.tipo] || "secondary";

                        const fila = `
                            <tr class="text-center">
                                <td><span class="badge bg-${colorClase}">${t.tipo}</span></td>
                                <td class="text-end">$${t.valor.toFixed(2)}</td>
                                <td>${fecha}</td>
                                <td>
                                    <button class="btn btn-sm btn-outline-primary" title="Editar"><i class="bi bi-pencil"></i></button>
                                    <button class="btn btn-sm btn-outline-secondary" title="Marcar"><i class="bi bi-bookmark"></i></button>
                                    <button class="btn btn-sm btn-outline-dark" title="Ver"><i class="bi bi-eye"></i></button>
                                </td>
                            </tr>`;
                        tbody.insertAdjacentHTML("beforeend", fila);
                    }
                });

            }
        }

    } catch (err) {
        console.error("❌ Error exacto:", err);
        alert("No se pudieron cargar los traslados.");
    }
}

document.getElementById("formTarifaUnica").addEventListener("submit", async function (e) {
    e.preventDefault();

    const origenId = parseInt(document.getElementById("origenSelect").value);
    const destinoId = parseInt(document.getElementById("destinoSelect").value);
    const unidadId = parseInt(document.getElementById("unidadIdInput").value);

    const traslado = {
        origenId: origenId,
        destinoId: destinoId,
        fechaSalida: document.getElementById("fechaSalidaInput").value,
        fechaLlegada: document.getElementById("fechaLlegadaInput").value,
        kilometrosRecorridos: parseFloat(document.getElementById("kilometrosInput").value),
        observaciones: document.getElementById("observacionesInput").value,
        unidadId: unidadId
    };

    try {
        const response = await axios.post("http://localhost:8080/api/traslados/crear-dto", traslado);
        console.log("✅ Traslado registrado:", response.data);

        // Actualizar tabla automáticamente si lo deseas
        cargarTrasladosPorUnidad(unidadId);

        // Cerrar el modal
        const modal = bootstrap.Modal.getInstance(document.getElementById("modalTarifaUnica"));
        modal.hide();
    } catch (error) {
        console.error("❌ Error al registrar traslado:", error);
        alert("Ocurrió un error al guardar el traslado.");
    }
});

document.querySelector('a[data-bs-target="#modalTarifaUnica"]').addEventListener("click", () => {
    const id = document.querySelector("input").value;
    document.getElementById("unidadIdInput").value = id;
});

// Cuando el modal de Tarifa Única se muestra, cargar ubicaciones
document.getElementById('modalTarifaUnica').addEventListener('show.bs.modal', () => {
    cargarUbicaciones();
});

document.querySelector(".btn-search").addEventListener("click", () => {
    const id = document.querySelector("input").value;
    cargarUnidad(id);
    cargarTrasladosPorUnidad(id);
});