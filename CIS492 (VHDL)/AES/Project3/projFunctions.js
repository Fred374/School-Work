var imgCount = 1;
var plypse = 0;
var started = 0;

function loadDoc() {
  const xhttp = new XMLHttpRequest();
  xhttp.open("GET", "http://cis.csuohio.edu/~sschung/CIS408/OnebusinessDataFormat_yelp.json");
  xhttp.send();
  text1 = xhttp.responseText;
  return text1;
}

function siteStart() {
	text1 = loadDoc();
	text1 = text1.replace('\n',' ');
	const obj = JSON.parse(text1);
	document.getElementById('name').innerHTML = obj.name;
	document.getElementById('address').innerHTML = obj.full_address;
	document.getElementById('mondayHrs').innerHTML = calcHours(obj.hours.Monday['open']) + ' - ' + calcHours(obj.hours.Monday['close']);
	document.getElementById('tuesdayHrs').innerHTML = calcHours(obj.hours.Tuesday['open']) + ' - ' + calcHours(obj.hours.Tuesday['close']);
	document.getElementById('wednesdayHrs').innerHTML = calcHours(obj.hours.Wednesday['open']) + ' - ' + calcHours(obj.hours.Wednesday['close']);
	document.getElementById('thursdayHrs').innerHTML = calcHours(obj.hours.Thursday['open']) + ' - ' + calcHours(obj.hours.Thursday['close']);
	document.getElementById('fridayHrs').innerHTML = calcHours(obj.hours.Friday['open']) + ' - ' + calcHours(obj.hours.Friday['close']);
	//if (obj.hours.Saturday['open'] != undefined) {
	//	document.getElementById('saturdayHrs').innerHTML = calcHours(obj.hours.Saturday['open']) + ' - ' + calcHours(obj.hours.Saturday['close']);
	//}
	//if (obj.hours.Sunday['open'] != undefined) {
	//	document.getElementById('sundayHrs').innerHTML = calcHours(obj.hours.Sunday['open']) + ' - ' + calcHours(obj.hours.Sunday['close']);
	//}
	
	var i = 1;
	if (obj.attributes['Take-out'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Take-out';
		i++;
	}
	if (obj.attributes['Drive-Thru'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Drive-Thru';
		i++;
	}
	document.getElementById('serv'+i).innerHTML = 'GOOD FOR';
	i++;
	if (obj.attributes['Good For']['dessert'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Dessert';
		i++;
	}
	if (obj.attributes['Good For']['latenight'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Latenight';
		i++;
	}
	if (obj.attributes['Good For']['lunch'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Lunch';
		i++;
	}
	if (obj.attributes['Good For']['dinner'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Dinner';
		i++;
	}
	if (obj.attributes['Good For']['brunch'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Brunch';
		i++;
	}
	if (obj.attributes['Good For']['breakfast'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Breakfast';
		i++;
	}
	if (obj.attributes['Caters'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Caters';
		i++;
	}
	document.getElementById('serv'+i).innerHTML = 'Noise Level - ' + obj.attributes['Noise Level'];
	i++;
	if (obj.attributes['Takes Reservations'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Takes Reservations';
		i++;
	}
	if (obj.attributes['Delivery'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Delivery';
		i++;
	}
	document.getElementById('serv'+i).innerHTML = 'AMBIENCE';
	i++;
	if (obj.attributes.Ambience['romantic'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Romantic';
		i++;
	}
	if (obj.attributes.Ambience['intimate'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Intimate';
		i++;
	}
	if (obj.attributes.Ambience['classy'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Classy';
		i++;
	}
	if (obj.attributes.Ambience['hipster'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Hipster';
		i++;
	}
	if (obj.attributes.Ambience['divey'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Divey';
		i++;
	}
	if (obj.attributes.Ambience['touristy'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Touristy';
		i++;
	}
	if (obj.attributes.Ambience['trendy'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Trendy';
		i++;
	}
	if (obj.attributes.Ambience['upscale'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Upscale';
		i++;
	}
	if (obj.attributes.Ambience['casual'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Casual';
		i++;
	}
	document.getElementById('serv'+i).innerHTML = 'PARKING';
	i++;
	if (obj.attributes.Parking['garage'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Garage';
		i++;
	}
	if (obj.attributes.Parking['street'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Street';
		i++;
	}
	if (obj.attributes.Parking['validated'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Validated';
		i++;
	}
	if (obj.attributes.Parking['lot'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Lot';
		i++;
	}
	if (obj.attributes.Parking['valet'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Valet';
		i++;
	}
	if (obj.attributes['Has TV'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Has TV';
		i++;
	}
	if (obj.attributes['Outdoor Seating'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Outdoor Seating';
		i++;
	}
	document.getElementById('serv'+i).innerHTML = 'Attire - ' + obj.attributes['Attire'];
	i++;
	document.getElementById('serv'+i).innerHTML = 'Alcohol - ' + obj.attributes['Alcohol'];
	i++;
	if (obj.attributes['Waiter Service'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Waiter Service';
		i++;
	}
	if (obj.attributes['Accepts Credit Cards'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Accepts Credit Cards';
		i++;
	}
	if (obj.attributes['Good for Kids'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Good For Kids';
		i++;
	}
	if (obj.attributes['Good For Groups'] == true) {
		document.getElementById('serv'+i).innerHTML = 'Good For Groups';
		i++;
	}
	document.getElementById('serv'+i).innerHTML = 'Price Range - ' + parsePrice(obj.attributes['Price Range']);
	i++;
}

function tabFunc(evt, tab) {
	switch(tab) {
		case 'name':
			if (!evt.currentTarget.className.includes('active')) {
				document.getElementById('name').style.display = "block";
			} else {
				document.getElementById('name').style.display = "none";
			}
			break;
		case 'address':
			if (!evt.currentTarget.className.includes('active')) {
				document.getElementById('address').style.display = "block";
			} else {
				document.getElementById('address').style.display = "none";
			}
			break;
		case 'hours':
			if (!evt.currentTarget.className.includes('active')) {
				document.getElementById('hours').style.display = "block";
			} else {
				document.getElementById('hours').style.display = "none";
			}
			break;
		case 'menu':
			if (!evt.currentTarget.className.includes('active')) {
				document.getElementById('menu').style.display = "block";
			} else {
				document.getElementById('menu').style.display = "none";
			}
			break;
		case 'services':
			if (!evt.currentTarget.className.includes('active')) {
				document.getElementById('services').style.display = "block";
			} else {
				document.getElementById('services').style.display = "none";
			}
			break;
	}
	var tabBtn = document.getElementsByClassName('tabButton');
	if (evt.currentTarget.className.includes('active')) {
		evt.currentTarget.className = evt.currentTarget.className.replace(' active', '');
	} else {
		evt.currentTarget.className += ' active';
	}
}

function calcHours(time) {
	var time2 = time.substring(0,2);
	var time3 = time.substring(2);
	var ampm = '';
	switch(time2) {
		case '12':
			ampm = 'pm'
			break;
		case '13':
			time2 = '1';
			ampm = 'pm';
			break;
		case '14':
			time2 = '2';
			ampm = 'pm';
			break;
		case '15':
			time2 = '3';
			ampm = 'pm';
			break;
		case '16':
			time2 = '4';
			ampm = 'pm';
			break;
		case '17':
			time2 = '5';
			ampm = 'pm';
			break;
		case '18':
			time2 = '6';
			ampm = 'pm';
			break;
		case '19':
			time2 = '7';
			ampm = 'pm';
			break;
		case '20':
			time2 = '8';
			ampm = 'pm';
			break;
		case '21':
			time2 = '9';
			ampm = 'pm';
			break;
		case '22':
			time2 = '10';
			ampm = 'pm';
			break;
		case '23':
			time2 = '11';
			ampm = 'pm';
			break;
		case '24':
			time2 = '12';
			ampm = 'am';
			break;
		default:
			ampm = 'am';
			break;
	}
	time = time2 + time3 + ' ' + ampm;
	return time;
}

function parsePrice(price) {
	switch(price) {
		case 1:
			price = '$';
			break;
		case 2:
			price = '$$';
			break;
		case 3:
			price = '$$$';
			break;
		case 4:
			price = '$$$$';
			break;
	}
	return price;
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