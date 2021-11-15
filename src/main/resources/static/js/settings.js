document.getElementById("saveProfile").disabled = true;
document.getElementById("name").disabled = true;
document.getElementById("email").disabled = true;
document.getElementById("editProfile").onclick = function(){
	document.getElementById("saveProfile").disabled = false;
	document.getElementById("name").disabled = false;
	document.getElementById("email").disabled = false;
	document.getElementById("editProfile").disabled = true;
}
console.log("Setting js")