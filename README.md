                   ENTREGA INICIAL DE XML

He subido unos diseños iniciales de XML, estos obviamente no serán finales hasta que no tenga la BBDD configurada con sus imágenes, texto y demás, para poder ver como encaja todo, que colores usar, etc.

Pero la base sobre la que cimentaré el proyecto es esta. Un fragment_main con 4 botones, a saber: escritores, sagas, libros y tiendas. Un fragment_view para cada una de esas 4 tablas de la BBDD, que será lo que se vea en los fragment_list de cada uno de ellos una vez esté el código listo, y un fragment_details con la información de cada columna referente al elemento seleccionado.

Además, desde los details de algún elemento concreto podremos acceder a los de aquello con lo que esté relacionado. Si entramos al de un escritor, podremos ver todas las sagas asociadas al mismo, desde cada saga podremos ver la información de todos sus libros, en cada libro veremos en que tienda está disponible, y desde cada tienda tendremos acceso a la lista de libros a la venta.

Por último, se podrán borrar entradas desde cada details, o añadir desde las lists.



                  PRIMERA ENTREGA FUNCIONAL

He hecho la pantalla de login mediante usuario y contraseña y hecho funcionales los botones del principal fragment para que lleven a los de las diferentes tablas de la BBDD, cogiendo la información relevante de la misma y mostrandola en un recyclerview. Lista de TODO para la siguiente entrega:

Relaciones entre tablas

Implementar funcionalidad de buscar

Añadir y borrar desde la propia app (el código para añadir ya está en AuthActivity)

Navegación al fragment del detail de cada elemento y cargar datos extra de la BBDD

Indicar el correo al que se está conectado junto al botón de desconexión

Modificar estilo de los activity y fragment

Conexión mediante google



                SEGUNDA ENTREGA FUNCIONAL

De la anterior lista, he añadido las relaciones entre tablas, añadir y borrar entradas, navegacion completa y alguna modificación del estilo. La aplicación ya está completamente funcional.
