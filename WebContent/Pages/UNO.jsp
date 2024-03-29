<%@page import="core.FileManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="author" content="MAL">
    <meta name="description" content="Pantalla de juego">
    <title>UNO</title>
    <script src="../jquery-3.4.1.min.js"></script>
    <script src="../scripts/DataManager.js"></script>
    <script src="../scripts/UNO.js"></script>
    <script src="../scripts/CookiesManager.js"></script>
    <script src="../scripts/SoundManager.js"></script>
    <script src="../scripts/RandomGenerator.js"></script>
    <script src="../scripts/Animator.js"></script>
    <link rel="stylesheet" type="text/css" href="../styles/UNO.css">



</head>

<body>
	<div class="font"><img id="backgroundImg" src="../images/GAME_FONT_01.jpg"></div>
	

    <div id="oponentCards"></div>
    <button id="personalize" onclick="backScreenChoosePicture.classList.add('active')" >Personalizar</button>
    <div id="deck" class="card" onclick="am.playerTakeCard();"> <img class="card" id="deck" src="../images/UNO.png"></div>
    <div id="discardPile" ></div>
    <div id="hand"></div>
    <button class="unoBtn" id="unoButton" onclick="am.playerPressUNO()"><img class="unoBtn" src="../images/BUTTON_UNO.png"></button>
    
    <div id="backScreenColor" class="backScreen">
        <div id="hostPopUp" class="popUp">
                <h1>UNO</h1>
                <h2>Elige el color</h2>
                <button id="RED" class="redBtn colorBtn" onclick="am.chooseColor(this.id)"></button>
                <button id="GREEN" class="greenBtn colorBtn" onclick="am.chooseColor(this.id)"></button>
                <br>
                <button id="BLUE" class="blueBtn colorBtn" onclick="am.chooseColor(this.id)"></button>
                <button id="YELLOW" class="yellowBtn colorBtn" onclick="am.chooseColor(this.id)"></button>
                
        </div>
    </div>

    <div id="backScreenDecision" class="backScreen">
        <div id="hostPopUp" class="popUp">
                <h1>UNO</h1>
                <h1>+4</h1>
                <h2>¿Retás a tu oponente?</h2>
                <button id="YES" class="decisionBtn" onclick="am.chooseDecision(this.id)">Si</button>
                <button id="NO" class="decisionBtn" onclick="am.chooseDecision(this.id)">NO</button>
        </div>
    </div>
    
    <div id="backScreenDrop" class="backScreen">
        <div id="hostPopUp" class="popUp">
                <h1>UNO</h1>
                <h2>Podés soltar la última carta que tomaste.</h2>
                <h2>¿Querés tirar la carta?</h2>
                <button id="YES" class="decisionBtn" onclick="am.dropConditionalCard(this.id); onDesition= false">Si</button>
                <button id="NO" class="decisionBtn" onclick="am.dropConditionalCard(this.id);  onDesition= false">NO</button>
        </div>
    </div>
    
    <div id="backScreenFinal" class="backScreen">
        <div id="hostPopUp" class="popUp">
                <h1>UNO</h1>
                <h2 id="lastMessage"></h2>
                <button class="decisionBtn" onclick="dataManager.destroy();setTimeout(location='index.jsp',5000);console.log('presioné el boton')">Volver a inicio</button>
        </div>
    </div>
    
    <div id="gameMessage" class="gameMessage" >
    </div>
    
    <div id="backScreenChoosePicture" class="backScreen">
    	<div id="picturesPopUp" class="popUp" >
		<h1>Escoge el fondo</h1>
    		<img style="width:15vw; height:15vh" onclick="backgroundImg.src=this.src;" src="../images/GAME_FONT_01.jpg">
    		<br>
    		<hr>
    		<img style="width:15vw; height:15vh" onclick="backgroundImg.src=this.src;" src="../images/GAME_FONT_02.jpg">
    		<br>    		
    		<hr>
    		<img style="width:15vw; height:15vh" onclick="backgroundImg.src=this.src;" src="../images/GAME_FONT_03.jpg">
    		<br>
    		<hr>
    		<img style="width:15vw; height:15vh" onclick="backgroundImg.src=this.src;" src="../images/GAME_FONT_04.jpg">
    		<br>
  			<button onclick="backScreenChoosePicture.classList.remove('active')" >Cerrar</button>
    	</div>
    </div>



</body>
    <script>
        var sm = new SoundManager();
        var animator = new Animator();
    	var lastOnDrop = "init";
    	var am = new ActionManager();
        var cookiesManager = new CookiesManager();
        var name = cookiesManager.getCookie("name");
        var info = {};
        var frontManager = new FrontManager(info);
        var dataManager = new DataManager();
        var idSetIntervalUpdate = null;
        var onDesition = false;
        updateFront();
        
        function updateFront(){
        	clearInterval(idSetIntervalUpdate);
        	idSetIntervalUpdate = setInterval(function(){
                clearInterval(idSetIntervalUpdate);
                dataManager.update();
            },700)
        }
        
    </script>

</html>