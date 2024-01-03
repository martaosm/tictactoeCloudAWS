
//const url = 'http://' + window.location.hostname + ':8080' //'http://ec2-107-21-69-73.compute-1.amazonaws.com:8080'
let stompClient;
let gameId;
let playerType;

function connectToSocket(gameId){
    console.log("connecting to the game");
    let socket = new SockJS(myGlobalVariable + "/gameplay");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame){
        console.log("connected to the frame: " + frame)
        stompClient.subscribe("/topic/game-progress/" + gameId, function (response){
            let data = JSON.parse(response.body);
            console.log(data);
            displayResponse(data);
        })
    })
}

function create_game(){
    let login = document.getElementById("login").value;
    if(login == null || login ===''){
        alert("Please enter login");
    }else{
        $.ajax({
            url: myGlobalVariable + "/game/start",
            type: 'POST',
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem("token"),
            },
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "login": login
            }),
            success: function (data) {
                gameId = data.gameId;
                playerType = 'X';
                reset();
                connectToSocket(gameId);
                alert("You created a game, Game id is: " + data.gameId);
                gameOn = true;
                document.getElementById("gameIDLabel").textContent = data.gameId;
            },
            error: function (error){
                console.log(error);
            }
        })
    }
}

function connectToRandom(){
    let login = document.getElementById("login").value;
    if(login == null || login ===''){
        alert("Please enter login");
    }else{
        $.ajax({
            url: myGlobalVariable + "/game/connect/random",
            type: 'POST',
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem("token"),
            },
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "login": login
            }),
            success: function (data) {
                gameId = data.gameId;
                playerType = 'O';
                reset();
                connectToSocket(gameId);
                alert("Congrats you're playing with: " + data.player1.login);
                // let socket = new SockJS(url + "/gameplay");
                // stompClient = Stomp.over(socket);
                // stompClient.connect({}, function (frame){
                //     stompClient.subscribe("/topic/game-progress/" + gameId, function (response){
                //         document.getElementById("player1Label").textContent = data.player1.login;
                //         document.getElementById("player2Label").textContent = data.player2.login;
                //     })
                // })
            },
            error: function (error){
                console.log(error);
            }
        })
    }
}

function connectToSpecificGame(){
    let login = document.getElementById("login").value;
    if(login == null || login ===''){
        alert("Please enter login");
    }else{
        let gameId = document.getElementById("game_id")
        if(gameId == null || gameId ===''){
            alert("Please enter game id");
        }
        $.ajax({
            url: myGlobalVariable + "/game/connect",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "player":{
                    "login": login
                },
                "gameId": gameId
            }),
            success: function (data) {
                gameId = data.gameId;
                playerType = 'O';
                reset();
                connectToSocket(gameId);
                alert("Congrats you're playing with: " + data.player1.login);
            },
            error: function (error){
                console.log(error);
            }
        })
    }
}