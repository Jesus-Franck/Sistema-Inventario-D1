# 🛒 Sistema de Gestión de Productos - Tienda D1 (JavaFX & MVC)

Este proyecto de laboratorio universitario es un sistema de inventario inspirado en las tiendas D1 de Colombia. Fue desarrollado para practicar el patrón de diseño de software **MVC (Modelo-Vista-Controlador)** utilizando **JavaFX**, **Scene Builder** y el sistema de módulos de Java en **VS Code**.

---

## 🚀 Funcionalidades

✅ **Registro de Productos:** Agrega nuevos productos especificando Nombre, Categoría, Precio, Cantidad y Descripción.  
🔎 **Consulta en tiempo real:** Visualización instantánea del inventario mediante un `TableView` sin recargas de pantalla pesadas.  
✏️ **Edición de Productos:** Modifica los datos de cualquier producto seleccionado directamente desde la tabla.  
🗑️ **Eliminación individual y masiva:** Borra un producto específico o vacía todo el inventario de un solo golpe.  
⚠️ **Validaciones Inteligentes:** Alertas de advertencia en pantalla para evitar campos vacíos o errores de selección.

---

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java 21+
* **Framework UI:** JavaFX + Scene Builder
* **Patrón de Arquitectura:** MVC (Model-View-Controller)
* **IDE Recomendado:** Visual Studio Code

---

## 📂 Estructura del Proyecto (MVC)

El proyecto está modularizado de la siguiente manera para mantener un código limpio:

```text
src/
└── main/
    ├── java/
    │   └── colecciones/
    │       ├── App.java                 # Clase principal que lanza la app
    │       ├── controller/
    │       │   ├── DashboardController.java # Controlador de la Vista de JavaFX
    │       │   └── ProductoController.java  # Lógica del ArrayList (CRUD)
    │       ├── model/
    │       │   └── Producto.java        # Clase POJO (Atributos del producto)
    │       └── module-info.java         # Gestión de permisos de JavaFX
    └── resources/
        └── colecciones/
            └── Dashboard.fxml          # Diseño de Scene Builder
----
