var app ={
	name:null
}
function changeSystemSettings(el){
	valCookie=$(el).val();
	$.cookie($(el).attr("id"), valCookie,{
		path:"/",
		expires:30
	});
	window.location.reload();
}

function makeCookie(){
	username="";
	password="";
	expireAt= new Date;
	expireAt.setMonth(expireAt.getMonth() + 1);
	username=$("#login").val();
	password=$("#pwd").val();
	document.cookie=app.name+"="+username+"$"+password+"; path=/; expires="+expireAt.toGMTString();
}

function key(event){
	if (event.keyCode==13){ 
		ourSubmit();
	}
}

function getCookie(name) {
	if($.cookie('lang') == null){
		$.cookie('lang', "RUS",{
			path:"/",
			expires:30
		});
	}
	if($.cookie('skin') == null){
		$.cookie('skin', "classic",{
			path:"/",
			expires:30
		});
	}
    var dc = document.cookie;
    var prefix = name + "=";
    var begin = dc.indexOf("; " + prefix);
    if (begin == -1) {
        begin = dc.indexOf(prefix);
        if (begin != 0) return null;
    } else {
        begin += 2;
    }
    var end = document.cookie.indexOf(";", begin);
    if (end == -1) {
        end = dc.length;
    }
    text=unescape(dc.substring(begin + prefix.length, end));
    document.form.login.value=text.split("$")[0];
    document.form.pwd.value=text.split("$")[1];
}

function ourSubmit(type){
	if($("#login").val() == ""){
		infoDialog("Введите имя пользователя")
		return;
	}else{
		if($("#pwd").val() =="" && type=="default"){
			infoDialog("Заполните пароль")
			return;
		}else{
	}
	$("form").submit();	}
}

function login_guest(type){
	$("#login").val("guest")
	$("#pwd").val("123")
	$("form").submit();
    return false;
}



function openListRegForm(){
	divhtml ="<div id='dialog-message' title='Выберите вид регистрации'>";
	divhtml +="<div style='height:40px; width:100%; text-align:center;'>"+
		"<font style='font-size:13px;'>Выберите необходимую форму регистрации</font></div>";
	divhtml += "</div>";
	$("body").append(divhtml);
	$("#dialog-message").dialog("destroy");
	$("#dialog-message").dialog({
		modal: true,
		buttons: {
			"Юридическое лицо": function() {
				window.location.href = 'Provider?type=edit&id=legalentity&key=';
				$(this).dialog("close").remove();
			},
			"Физическое лицо": function() {
				window.location.href = 'Provider?type=edit&id=naturalperson&key=';
				$(this).dialog("close").remove();
			},
			"Ответственное лицо по загрузке объектов": function() {
				window.location.href = 'Provider?type=edit&id=responsibleperson&key=';
				$(this).dialog("close").remove();
			},
		},
		 beforeClose: function() { 
			 $("#dialog-message").remove();
		} 
	});
	$(".ui-dialog button").focus();
}