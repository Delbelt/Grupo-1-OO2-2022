# Proyecto: Sistema de Gestión de Aulas - OO2 UNLA
 
## Integrantes Grupo 1:

> #### Matias Basilio
> 
> #### Gonzalo Beelmann
>
> #### Nahuel Flores
>
> #### Marcos Gaitan

### Pasos a seguir para probar el programa:
```
1) Crear la base de datos: create database if not exists `grupo-1-BD-OO2-2022`;
2) Ejecutar por primera vez el programa para generar todas las tablas del proyecto
3) Ejecutar el script SQL: g1-oo2-gestionAulas para generar los datos por defectos para testearlo
4) Aparecera la pantalla de LOGIN para ingresar con el usuario y contraseña
5) Una vez logeado, te redireccionara al inicio, click en "INGRESAR PROFESORES" para ver el camino de Nota Pedido
```

### Paquetes del proyecto
| Paquete | Descripcion |
| ------ | ------ |
| controller | Controladores de la aplicacion |
| entities | Entidades - Clases Java|
| repositories | Persistencia JPA - Interfaces |
| security | Seguridad Spring - Configuracion |
| services | Firmas de los servicios - Interfaces |
| implementations | Logica de negocio de los servicios - Clases Java |
| web | Configuraciones Web - WebMvcConfigurer|
| util | Rutas y listado de Clientes PDF|
| ------ | ------ |
| static | Archivos: JS, CSS, IMG, etc |
| styles | Diseños CSS |
| ------ | ------ |
| templates | aca tienen que ir los archivos HTML - Vista |
| components | Componentes para re-utilizar en otras vistas |
| layout | Plantilla para reutilizar sus diseños estaticos |
| error | Paginas de errores (403, 404, 500, etc) |
| nombres de clases | Para organizar las funcionalidades por clase |