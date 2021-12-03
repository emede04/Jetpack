package com.example.lazy_peliculas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.lazy_peliculas.ui.theme.Lazy_PeliculasTheme
import com.example.lazy_peliculas.ui.theme.pantallazo

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lazy_PeliculasTheme {
                Surface(color = Color.Black) {
                    NavigationHost()
                }
            }
        }
    }
}

fun getLista(): List<Pelicula> {

    return listOf(
        Pelicula(
            "Nosferatu",
            "F.W. Murnau",
            1922,
            "A symphony of horror\n" +
                    "\n" +
                    "Vampire Count Orlok is interested in a new residence and in his real estate agent’s young wife. F. W. Murnau’s unauthorized adaptation of Bram Stoker’s “Dracula.”\n",

            "https://a.ltrbxd.com/resized/film-poster/5/1/4/7/1/51471-nosferatu-0-230-0-345-crop.jpg?k=71833878c8",
                "https://a.ltrbxd.com/resized/sm/upload/ph/g3/tr/ws/nosferatu-1200-1200-675-675-crop-000000.jpg?k=a99eae10d6",
        ),
        Pelicula(
            "Mommy",
            "Xavier Dolan",
        2014,
            "Loving people doesn’t save them\n" +
                    "\n" +
                    "A peculiar neighbor offers hope to a recent widow who is struggling to raise a teenager who is unpredictable and, sometimes, violent.\n",

"https://a.ltrbxd.com/resized/film-poster/1/8/8/4/2/1/188421-mommy-0-230-0-345-crop.jpg?k=7a098b5ef0",
    "https://a.ltrbxd.com/resized/sm/upload/hj/ar/9s/ku/mommy-1200-1200-675-675-crop-000000.jpg?k=189141d092"

        ),
        Pelicula(
            "Los olvidados",
            "Luis Buñuel",
            1950,
            "A group of juvenile delinquents lives a criminal, violent life in the festering slums of Mexico City, among them the young Pedro, whose morality is gradually corrupted and destroyed by the others",
            "https://a.ltrbxd.com/resized/film-poster/5/1/3/5/2/51352-los-olvidados-0-230-0-345-crop.jpg?k=b87b916a9e",
            "https://a.ltrbxd.com/resized/sm/upload/kg/6s/ae/h2/los-olvidados-1200-1200-675-675-crop-000000.jpg?k=7d1be427f8",

        )




    )


}


@Composable
fun inicio(navController: NavController) {
    LazyColumn(
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    MaterialTheme.colors.onSecondary,
                    MaterialTheme.colors.primaryVariant,
                )))

        )
    {
        items(getLista()) { peli ->
             Card(

                modifier = Modifier

                    .clickable(onClick = {
                        navController.navigate(
                            pantallazo.info.decaminoa(
                                peli.nombre
                            )
                        )
                    })
                    .background(MaterialTheme.colors.primary,
             )
                    .padding(horizontal = 1.dp, vertical = 2.dp)
                    .fillMaxSize(), elevation = 2.dp,

                     shape = RectangleShape,


            )

            {
                CargarImagen(url = peli.imagePadding)


            }
        }
    }
}

@Composable
fun info(Nombre: String, navController: NavController) {
    val cartelera = getLista()
    var peliselect = Pelicula("", "", 0, "", "","")

    for (pelicula in cartelera) {
        if (pelicula.nombre == Nombre) {
            peliselect = pelicula
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colors.onSecondary,
                        MaterialTheme.colors.primaryVariant,
                    )))
            .fillMaxWidth()
            .fillMaxHeight()

            .padding(14.dp)
    ) {


        Row(
            modifier = Modifier
                .background( MaterialTheme.colors.onSecondary)
                .fillMaxWidth()
                .padding(start = 10.dp)
        ) {
            Text(
                text = peliselect.nombre,
                color = Color.White,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                lineHeight = 2.em,
            )
        }

        Row(

            modifier = Modifier

                .width(200.dp)
                .height(250.dp)
        ) {
            CargarImagenCompleta(url = peliselect.imagen)
        }


        Row {
            Text(
                text =  peliselect.autor,
                color = Color.White,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                lineHeight = 2.em
            )
        }

        Row {
            Text(
                text = peliselect.año.toString(),
                color = Color.White,
                lineHeight = 2.em,
                textAlign = TextAlign.Center
            )
        }


        Row {
            Text(
                text = peliselect.descripcion,
                color = Color.White,
                lineHeight = 2.em,
                textAlign = TextAlign.Center
            )
        }

        Button(
            onClick = { navController.navigate(pantallazo.inicio.ruta) }, modifier = Modifier
                .padding(top = 35.dp)
                .background(Color.Blue)
        ) {
            Text(text = "Pulse para volver a la pantalla de inicio"

            )
                       }
    }
}



@Composable
fun NavigationHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = pantallazo.inicio.ruta) {
        composable(pantallazo.inicio.ruta) {
            inicio(navController = navController)
        }

        composable(pantallazo.info.ruta) { navBackStackEntry ->
            var nombre = navBackStackEntry.arguments?.getString("nombre")
            info(nombre!!, navController = navController)
        }
    }

}


@Composable
fun CargarImagen(url: String) {
    Image(
        painter = rememberImagePainter(url),
        contentDescription = "imagePadding",
        modifier = Modifier

            .padding(1.dp)
            .height(120.dp)

        , alignment = Alignment.BottomStart ,

    contentScale = ContentScale.FillBounds)
}
@Composable
fun CargarImagenCompleta(url: String) {
    Image(
        painter = rememberImagePainter(url), contentDescription = "Imagen",
        modifier = Modifier
            .height(220.dp)
            .width(220.dp)
            .padding(0.dp),

        contentScale = ContentScale.Fit
        ,
    )
}
