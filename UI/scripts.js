var id = 0;
var username = "";
var messList = [];
flag = new Boolean(true);

function run(){
	var appContainer = document.getElementsByClassName('container')[0];

	appContainer.addEventListener('click', delegateMessage);
	appContainer.addEventListener('keydown', delegateMessage);

	
	messList = restoreUserMess() || [newMessage('Chat launched')];
	id = messList[messList.length - 1].identificator;

	username = restoreUserName() || 'Default user';
	var input = document.getElementById('name');
	input.value = username;

	updateHistory(messList);

	var box = document.getElementById('Box');
    box.scrollTop += 9999;
}

function delegateMessage(evtObj) {
	if((evtObj.type === 'click' && evtObj.target.classList.contains('changename-button')) ||
		(evtObj.type === 'keydown' && evtObj.target.classList.contains('todo-input1') && evtObj.keyCode == 13)) {
		changeName(evtObj);
	}
	if((evtObj.type === 'click' && evtObj.target.classList.contains('todo-button')) ||
		(evtObj.type === 'keydown' && evtObj.target.classList.contains('todo-input2') && evtObj.keyCode == 13)) {
		sendMessage(evtObj);
	}
}

function newMessage(text) {
    id++;

    return {
        nickname: username,
        identificator: id,
        messtext: text,
        timer: new Date().toTimeString().replace(/.*(\d{2}:\d{2}:\d{2}).*/, "$1"),
        deleted: false,
        red: false
    };
}

function updateHistory(messList) {
	document.getElementById('list').innerHTML = "";

    for (var i = 0; i < messList.length; i++) {
    	showUpdatedHistory(messList[i]);
    }
   	
    storeUserMess(messList);
}

function sendMessage() {
    var todoText = document.getElementById('todoText');
    var text = todoText.value;

    if(text != "") {
    	messList.push(newMessage(text));
    	updateHistory(messList);
    	todoText.value = "";
    }

    flag = true; 

    var box = document.getElementById('Box');
    box.scrollTop += 9999;  
}


function changeName() {
    var input = document.getElementById('name');

    username = input.value;
    storeUserName(username);
    updateHistory(messList);
    
    flag = true;

    var box = document.getElementById('Box');
    box.scrollTop += 9999; 
}

function showUpdatedHistory(mess) {
	var divItem = document.createElement('li');
	var divName = document.createElement('li');
	var textName = document.createElement('div');
	var textItem = document.createElement('div');
	var time = document.createElement('div');
	var box = document.getElementById('Box');
	var redPic = document.createElement('IMG');
	
	time.classList.add('time');
	divItem.classList.add('item');
	divName.classList.add('name');
		time.setAttribute('id', 't' + mess.identificator);
	divItem.setAttribute('id', 'divId' + mess.identificator);
	divName.setAttribute('id', 'divName' + mess.identificator);
	textItem.setAttribute('id', 'textDiv' + mess.identificator);


	if(username === mess.nickname) {
		var inputRed = document.createElement('input');
		
		if(!mess.deleted) {
			var but1 = document.createElement('button');
			var but2 = document.createElement('button');
		
			but1.classList.add('delBut');
			but2.classList.add('redBut');
		
			but1.setAttribute('id','del' + mess.identificator);
    		but2.setAttribute('id','red' + mess.identificator);
    		but2.setAttribute('title','Click to open \nDouble-click to close');
    	
			but1.addEventListener('click', function(){
				deleteMessage(mess);
			});

			but2.addEventListener('click', function(){
				if(flag == true) {
					changeMessage(mess);
				} 
			});

			but2.addEventListener('dblclick', function(){
				changeMessage2(mess);
			});

			inputRed.addEventListener('keydown', function(e){
				if(e.keyCode == 27){
					changeMessage3(mess);
				}
			});

			divItem.appendChild(but2);
			divItem.appendChild(but1);
		}

		inputRed.classList.add('In');
		inputRed.setAttribute('id', 'textIn' + mess.identificator);
		inputRed.hidden = true;

		divItem.appendChild(inputRed);
		 	
	}

	if(mess.red === true) {
		redPic.setAttribute('id', 'redPi' + mess.identificator);
		redPic.classList.add('pika-pika');
		redPic.src = "images/gmp.png";
		
		divName.appendChild(redPic);
	}

	textItem.innerHTML = mess.messtext;
	textName.innerHTML = mess.nickname;
	time.innerHTML = mess.timer;

	divName.appendChild(time);
	divName.appendChild(textName);

	divItem.appendChild(textItem);

	document.getElementById('list').appendChild(divName);
	document.getElementById('list').appendChild(divItem);
}


function restoreUserName() {
	if(typeof(Storage) == "undefined") {
		alert('localStorage is not accessible');
		return;
	}

	var item = localStorage.getItem("User's name");
	return item && JSON.parse(item);;
}

function storeUserName(listToSave) {
	if(typeof(Storage) == "undefined") {
		alert('localStorage is not accessible');
		return;
	}

	localStorage.setItem("User's name", JSON.stringify(listToSave));
}

function restoreUserMess() {
	if(typeof(Storage) == "undefined") {
		alert('localStorage is not accessible');
		return;
	}

	var item = localStorage.getItem("User's mess");
	return item && JSON.parse(item);
}

function storeUserMess(listToSave) {
	if(typeof(Storage) == "undefined") {
		alert('localStorage is not accessible');
		return;
	}

	localStorage.setItem("User's mess", JSON.stringify(listToSave));	
}

function deleteMessage(mess) {
	mess.messtext = 'Deleted...';
	mess.deleted = true;
	mess.red = false;
	flag = true;
	updateHistory(messList);
}

function changeMessage(mess) {
	var textItem = document.getElementById('textDiv' + mess.identificator);
	var inputRed = document.getElementById('textIn' + mess.identificator);

	textItem.hidden = true;
	inputRed.hidden = false;

	inputRed.value = mess.messtext;

	flag = false;

	inputRed.addEventListener('keydown', function(e) {
		if(e.keyCode == 13 && username === mess.nickname) {
			if(mess.messtext != inputRed.value) {
				mess.red = true;
			}
			
			mess.messtext = inputRed.value;
			inputRed.hidden = true;
			textItem.hidden = false;
		
			updateHistory(messList);
			flag = true;
		}
	});
}

function changeMessage2(mess) {
	var textItem = document.getElementById('textDiv' + mess.identificator);
	var inputRed = document.getElementById('textIn' + mess.identificator);
	inputRed.hidden = true;
	textItem.hidden = false;
	flag = true; 
}

function changeMessage3(mess) {
	var textItem = document.getElementById('textDiv' + mess.identificator);
	var inputRed = document.getElementById('textIn' + mess.identificator);
	inputRed.hidden = true;
	textItem.hidden = false;
	flag = true; 
}



		





	






