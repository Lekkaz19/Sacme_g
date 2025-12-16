# Sistema de Gestión de Materiales (Jakarta Faces)

## Requisitos Previos
- **Java JDK 17+**
- **Apache NetBeans 15+** (o Eclipse/IntelliJ)
- **Servidor de Aplicaciones:** WildFly 27+ o Payara 6 (Compatible con Jakarta EE 10)
- **MySQL Server 8.0+**

## 1. Configuración de Base de Datos (MySQL)

1. Abrir su cliente MySQL (Workbench, heidiSQL, consola).
2. Crear la base de datos:
   ```sql
   CREATE DATABASE db_inventario;
   ```
3. Ejecutar los scripts SQL proporcionados en el proyecto:
   - Primero: `sql/schema.sql` (Crea las tablas)
   - Segundo: `sql/data.sql` (Inserta datos de prueba)

   *Nota: El archivo `persistence.xml` está configurado para intentar cargar los datos automáticamente (`drop-and-create`), pero crear la DB manualmente es recomendado.*

4. Verificar credenciales:
   - El proyecto asume usuario `root` y contraseña `root`.
   - Si sus credenciales son diferentes, edite el archivo:
     `src/main/resources/META-INF/persistence.xml`

## 2. Ejecución en Apache NetBeans

1. **Abrir Proyecto:**
   - Menú Archivo > Abrir Proyecto.
   - Navegar y seleccionar la carpeta del proyecto (donde está el `pom.xml`).

2. **Configurar Servidor (si no lo ha hecho):**
   - Pestaña "Services" (Servicios) > Servers (Servidores).
   - Click derecho > Add Server.
   - Seleccionar **WildFly Application Server** o **Payara Server**.
   - Descargar/Ubicar la carpeta del servidor.

3. **Ejecutar:**
   - Click derecho en el proyecto -> **Run** (o presionar F6).
   - Seleccionar el servidor configurado si se solicita.
   - NetBeans compilará el proyecto, iniciará el servidor y desplegará el WAR.

4. **Acceso:**
   - El navegador debería abrirse automáticamente.
   - URL típica: `http://localhost:8080/material-management/`

## Solución de Problemas Comunes
- **Error de Conexión DB:** Verifique que el servicio MySQL esté corriendo y las credenciales en `persistence.xml` sean correctas.
- **Error "Driver not found":** Maven debería descargar el driver de MySQL, pero asegúrese de que el Servidor no requiera el driver instalado como módulo (especialmente en WildFly modo JTA, aunque este proyecto usa RESOURCE_LOCAL para facilitar el despliegue simple).
