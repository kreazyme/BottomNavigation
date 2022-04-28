package com.midterm.navigation


import android.R.id
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.midterm.navigation.ui.theme.NavigationTheme
import kotlin.math.round


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            NavigationTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    Greeting("Android")
//                }
//            }
            MainScreen()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}


var AppbarName = "Home"

@Composable
fun MainScreen() {
    val maincontroler = rememberNavController()
    Scaffold(
        topBar = { TopAppBar(title = {Text(
            AppbarName, style = TextStyle(
            color = Color.White,
            fontSize = 24.sp
        )
        )},backgroundColor = Color(0xff28965A))  },
        bottomBar = {BottomNavigationBar(navControler = maincontroler)}) {
        NavigationGraph(navControler = maincontroler)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NavigationTheme {
        Greeting("Android")
    }
}


@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .background(colorResource(id = R.color.holo_blue_bright))
            .background(Color(0xff7CFEF0))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Home Screen",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 30.sp
        )
    }
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff6BFFB8))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Profile Screen",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 30.sp
        )
    }
}

@Composable
fun MusicScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff2CEAA3))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Music Screen",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 30.sp
        )
    }
}

@Composable
fun SettingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff28965A))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Setting Screen",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 30.sp
        )
    }
}

@Composable
fun NavigationGraph(navControler: NavHostController) {
    NavHost(navController = navControler, startDestination = NavItem.Home.rout_string){
        composable(NavItem.Home.rout_string) {
            HomeScreen()
        }
        composable(NavItem.Music.rout_string){
            MusicScreen()
        }
        composable(NavItem.Profile.rout_string){
//            ProfileScreen()
            CreateView()
        }
        composable(NavItem.Setting.rout_string){
            TipsScreen()
        }
    }
}

fun ChangeAppbarTitle(text: String){
    AppbarName = text;
}

@Composable
fun BottomNavigationBar(navControler: NavController) {

    val items= listOf(
        NavItem.Home,
        NavItem.Music,
        NavItem.Profile,
        NavItem.Setting
    )
    BottomNavigation(
        backgroundColor = Color(0xff2A6041),
        contentColor = Color(0xFFFFFFFF)
    ){
        val navBackStackEntry by navControler.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach{
            item ->
            BottomNavigationItem(
                selected = currentRoute == item.rout_string,

                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title)},
                label = { Text(text = item.title)},
                onClick = {
                    navControler.navigate(
                        item.rout_string
                    ){
                        navControler.graph.startDestinationRoute?.let {
                            rout_string -> popUpTo(rout_string){
                                saveState = true
                        }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }
    }
    
}

@Composable
fun CreateTotalPerPerson(totalPerPerson: Double){
    Card(
        modifier = Modifier,
        shape = RoundedCornerShape(corner = CornerSize(32.dp)),
    ){
        Column(
            modifier = Modifier
                .width(350.dp)
                .padding(3.dp)
                .height(150.dp)
                .background(Color(0xff28965A)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = "Total Per Person",
                style = MaterialTheme.typography.h4,
                color = Color.Black,
                fontSize = 25.sp
            )
            Text(text = "$"+"${totalPerPerson}",
                style = MaterialTheme.typography.h3,
                color = Color.Black,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

}


@Composable
fun TipTable(total: Double) : Double{
    var counter = remember {
        mutableStateOf(1)
    }
    var totalReturn = remember {
        mutableStateOf(0.0)
    }
    var tip = 0.0;
    Column(

    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Split")
            Spacer(modifier = Modifier.width(150.dp))
            CreateCircle(counter = counter.value,"-"){
                    newValue-> counter.value = newValue
            }
            Spacer(modifier = Modifier.width(20.dp))
            Text(text="${counter.value}", style= TextStyle(color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold)
            )
            Spacer(modifier = Modifier.width(20.dp))
            CreateCircle(counter = counter.value,"+"){
                    newValue-> counter.value = newValue
            }
        }
        var sliderPosition = remember { mutableStateOf(0f) }
        Row(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(text = "Tip")
            Spacer(modifier = Modifier.width(250.dp))
            tip = total*sliderPosition.value.toInt()/100;
            Text(text = "$"+"${tip}")
        }
        Row(
            modifier = Modifier.padding(12.dp)
        ) {
            Spacer(modifier = Modifier.width(150.dp))
            Text(text = "${sliderPosition.value.toInt()}"+"%")
        }
        Row(
            modifier = Modifier.padding(12.dp)
        ){
            var valueRange = 0f..100f
            Slider( steps = 10,

                value = sliderPosition.value, valueRange = valueRange,
                colors = SliderDefaults.colors(
                    thumbColor = Color(0xff28965A),
                    activeTrackColor = Color(0xff28965A)
                ),
                onValueChange = {sliderPosition.value = it})
        }
    }
    totalReturn.value = (total+tip)/counter.value ;
    return round(totalReturn.value*100)/100;
}

@Composable
fun CreateCircle(counter: Int=1,  dau: String,countUp: (Int) -> Unit){
    Card(modifier = Modifier
        .padding(3.dp)
        .size(50.dp)
        .clickable {
            if (dau == "+") {
                countUp(counter + 1)
            } else if (counter > 1) {
                countUp(counter - 1)
            }

        },

        shape = CircleShape,
        elevation = 4.dp) {
        Box(contentAlignment = Alignment.Center){
            if(dau=="+"){
                Text("+")
            }
            else{
                Text("-")
            }


        }

    }
}

@Composable
fun TipsScreen() {
    var totalPerPerson = remember {
        mutableStateOf(0.0)
    }
    var total = remember {
        mutableStateOf("")
    }
    var check = 0

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth(),
        color = MaterialTheme.colors.background
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(390.dp)
                .absolutePadding(24.dp, 40.dp, 24.dp, 0.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            elevation = 4.dp,
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CreateTotalPerPerson(totalPerPerson = totalPerPerson.value)
                OutlinedTextField(modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),

                    value = "${total.value}", onValueChange = {
                        total.value = it;
                        check = 1
//                                if(it==""){
//                                    totalPerPerson.value=0.0
//                                }
//                                else{
//                                    totalPerPerson.value= it.toDouble()
//                                }

                    }, label = { Text(text = "Enter bill", style = TextStyle(
                        fontSize =20.sp,
                        color = Color(0xff28965A)
                    )
                    ) },
                    textStyle = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xff28965A)
                    ),
                    leadingIcon = { Text(text = "$", style = TextStyle(
                        fontSize = 24.sp
                    )) },
                    keyboardOptions =
                    KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                )


                if (check == 1) {
                    if (total.value == "") {
                        totalPerPerson.value = TipTable(0.0);
                    } else {
                        totalPerPerson.value = TipTable(total.value.toDouble());
                    }

                }
            }
        }}
}

@Composable
fun CreateView(){
    Column(modifier = Modifier.padding(20.dp).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(
            painter = painterResource(R.drawable.cat1),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,            // crop the image if it's not a square
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)                       // clip to the circle shape
                .border(2.dp, Color.White, CircleShape)   // add a border (optional)
        );
        Text(text = "Trần Đức Thông", style = TextStyle(
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,

            color = Color(0xff28965A)
        ),
            modifier = Modifier.padding(vertical = 6.dp)
        );
        Text(text = "Android Programmer VIP Pro123", style = TextStyle(
            fontSize = 20.sp
        ));
        Text(text = "@tranducthong", style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Light
        ));



        var ls = ArrayList<Skill>();
        ls.add(Skill(R.drawable.cat1,"Project 1", "Description of Project 1"))
        ls.add(Skill(R.drawable.cat2,"Project 2", "Description of Project 2"))
        ls.add(Skill(R.drawable.cat3,"Project 3", "Description of Project 3"))
        ls.add(Skill(R.drawable.cat4,"Project 4", "Description of Project 4"))
        ls.add(Skill(R.drawable.cat1,"Project 1", "Description of Project 1"))
        ls.add(Skill(R.drawable.cat2,"Project 2", "Description of Project 2"))
        ls.add(Skill(R.drawable.cat3,"Project 3", "Description of Project 3"))
        ls.add(Skill(R.drawable.cat4,"Project 4", "Description of Project 4"))
        ls.add(Skill(R.drawable.cat1,"Project 1", "Description of Project 1"))
        ls.add(Skill(R.drawable.cat2,"Project 2", "Description of Project 2"))
        ls.add(Skill(R.drawable.cat3,"Project 3", "Description of Project 3"))
        ls.add(Skill(R.drawable.cat4,"Project 4", "Description of Project 4"))
        ls.add(Skill(R.drawable.cat1,"Project 1", "Description of Project 1"))
        ls.add(Skill(R.drawable.cat2,"Project 2", "Description of Project 2"))
        ls.add(Skill(R.drawable.cat3,"Project 3", "Description of Project 3"))
        ls.add(Skill(R.drawable.cat4,"Project 4", "Description of Project 4"))



        val showPortifilo = remember {
            mutableStateOf(false)
        }

        Button(
            onClick =
            {
                showPortifilo.value = !showPortifilo.value

            },
            modifier = Modifier.padding(all = 20.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xff28965A)
            )

        ) {
            Text(text = "Portfolio", style = TextStyle(
                fontSize = 26.sp,
                color = Color.White
            ))
        };
        if(showPortifilo.value) {
            Portfolio(ls)
        }
    }
}

@Composable
fun Portfolio(data: ArrayList<Skill>) {
    LazyColumn {
        items(data) { item ->
            Card(modifier = Modifier.padding(vertical = 10.dp)) {
                Row() {
                    Image(

                        painter = painterResource(item.Imageres),
                        contentDescription = "avatar",
                        contentScale = ContentScale.Crop,
                        // crop the image if it's not a square
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
//                            .padding(vertical = 10.dp)
//                .border(2.dp, Color.CYAN, CircleShape)   // add a border (optional)
                    );
                    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                        Text(
                            text = item.Name,
                            style = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Normal)
                        );
                        Text(
                            text = item.Descrip,
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Light)
                        )
                    }
                }
            }
        }
    }
}


