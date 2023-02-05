He subido unos diseños iniciales de XML, estos obviamente no serán finales hasta que no tenga la BBDD configurada con sus imágenes, texto y demás, para poder ver como encaja todo, que colores usar, etc.

Pero la base sobre la que cimentaré el proyecto es esta. Un fragment_main con 4 botones, a saber: escritores, sagas, libros y tiendas. Un fragment_view para cada una de esas 4 tablas de la BBDD, que será lo que se vea en los fragment_list de cada uno de ellos una vez esté el código listo, y un fragment_details con la información de cada columna referente al elemento seleccionado.

Además, desde los details de algún elemento concreto podremos acceder a los de aquello con lo que esté relacionado. Si entramos al de un escritor, podremos ver todas las sagas asociadas al mismo, desde cada saga podremos ver la información de todos sus libros, en cada libro veremos en que tienda está disponible, y desde cada tienda tendremos acceso a la lista de libros a la venta.

Por último, se podrán borrar entradas desde cada details, o añadir desde las lists.