var imgCount = 1;
var plypse = 0;
var started = 0;

function tabFunc(evt, tab) {
	document.getElementById('tabDisplayFrame').src=tab;
	var tabBtn = document.getElementsByClassName("tabButton");
	for(var i=0; i<tabBtn.length; i++) {
		tabBtn[i].className = tabBtn[i].className.replace(" active", "");
	}
	evt.currentTarget.className += " active";
	
}

function imgFunc(i) {
	if (i == 2 && imgCount < 3) {
		imgCount++;
	} else if (i == 2 && imgCount == 3) {
		imgCount = 1;
	} else if (i == 1 && imgCount > 1) {
		imgCount--;
	} else if (i == 1 && imgCount == 1) {
		imgCount = 3;
	}
	document.getElementById('img1').src="pic"+imgCount+".jpg";
}

function vidFunc(evt, i) {
	if (i == 1 && plypse == 0 && started == 0) {
		plypse = 1;
		started = 1;
		document.getElementById('vidBtn1').innerHTML = '||';
		document.getElementById('vid1').play();
		vidStart();
	} else if (i == 1 && plypse == 0) {
		plypse = 1;
		document.getElementById('vidBtn1').innerHTML = '||';
		document.getElementById('vid1').play();
	} else if (i == 1 && plypse == 1) {
		plypse = 0;
		document.getElementById('vidBtn1').innerHTML = '>';
		document.getElementById('vid1').pause();
	} else if (i == 2) {
		document.getElementById('vid1').currentTime = 0;
	} else if (i == 3) {
		document.getElementById('vid1').currentTime -= 10;
	} else if (i == 4) {
		document.getElementById('vid1').currentTime += 10
	}
}

function vidStart() {
	setInterval(function vidFunc2() {
		var vid = document.getElementById('vid1');
		var curTime = vid.currentTime.toFixed(0);
		var vidTime = (curTime/3600).toFixed(0) + ':' + ((curTime%3600)/60).toFixed(0) + ':' + (curTime%60).toFixed(0);
		var dur = vid.duration.toFixed(0);
		var vidDur = (dur/3600).toFixed(0) + ':' + ((dur%3600)/60).toFixed(0) + ':' + (dur%60).toFixed(0);
		document.getElementById('vidTime').innerHTML = vidTime;
		document.getElementById('vidDuration').innerHTML = vidDur;
	}, 500)
}

function myFunction() {
  var x = document.getElementById("form1").action;
  document.getElementById("demo").innerHTML = x;
}

function formFunc1() {
	var fname = document.getElementsByName('fName')[0].value;
	var lname = document.getElementsByName('lName')[0].value;
	var email = document.getElementsByName('email')[0].value;
	var action = document.getElementById('form1').action;
	var string = 'fname: ' + fname + '<br> lname: ' + lname + '<br> email: ' + email + '<br>' + action;
	document.getElementById('list2').innerHTML = string;
}

function animate() {
	x += 1;
	myContext.clearRect(0, 0, myCanvas.width, myCanvas.height);
	myContext.drawImage(img, x, 0, 145, 145);
	window.requestAnimationFrame(animate);
	if (x == myCanvas.width)
		x = 0;
}