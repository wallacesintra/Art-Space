package com.example.artspace

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}


class ArtDetails(
    val id: Int,
    val artName: String,
    val artistName: String,
    val date: Int,
    val artImage: Int
){}

var arts = arrayOf(
    ArtDetails(id = 1, artName = "Picasso", artistName = "Picasso", date = 2019, artImage = R.drawable.test),
    ArtDetails(id = 2, artName = "Monalisa", artistName = "Leonard", date = 1779, artImage = R.drawable.mfdoom),
    ArtDetails(id = 3, artName = "Hand of God", artistName = "Michael", date = 1809, artImage = R.drawable.skull),
)



@Composable
fun ImageContainer(modifier: Modifier = Modifier, img: Int){
    Box (

        modifier = Modifier
            .background(Color(0xFFCCCCFF))
            .fillMaxWidth()
            .height(300.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = img),
            contentDescription = null,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .fillMaxHeight(),

        )
    }

}
//artwork info
@Composable
fun ArtInfo(modifier: Modifier = Modifier, artistName: String, date: Int,title: String){
    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .background(Color(0xFFCCCCFF))
            .padding(10.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
        )
        Text(
            text = "$artistName ($date)",
            style = MaterialTheme.typography.bodyMedium
        )
    }

}

//buttons
@Composable
fun ButtonWrapper(modifier: Modifier = Modifier, prev: () -> Unit, next: () -> Unit){
    Row(
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()

    ){
        Button(
            onClick = prev,
            modifier = Modifier
                .width(110.dp)
                .height(40.dp)
        ) {
            Text(text = stringResource(R.string.previous))
        }
        Button(
            onClick = next,
            modifier = Modifier
                .width(110.dp)
                .height(40.dp)

        ) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}


@Composable
fun ArtSpaceLayout(

//    modifier: Modifier
){
    var i by remember{ mutableStateOf(0)}
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(30.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ImageContainer(img = arts[i].artImage)
        Spacer(modifier = Modifier.height(30.dp))
        ArtInfo(
            title = arts[i].artName,
            artistName = arts[i].artistName,
            date = arts[i].date
        )
        Spacer(modifier = Modifier.height(30.dp))
        ButtonWrapper(prev = {if (i in (0..arts.size)) {i -- } },
            next = {if (i in (0..arts.size)){i ++ } })
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}