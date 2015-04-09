//set utf-8 құ

function changeSystemSettings(el){
	valCookie=$(el).val();

	$.cookie($(el).attr("id"), valCookie,{
		path:"/",
		expires:"30"
	});

	window.location.reload();
}

function makeCookie(){
	expireAt= new Date();

	if(document.getElementById("noauth").checked==false){
		expireAt.setMonth(expireAt.getMonth() + 1);
		document.cookie="user="+document.getElementById("login").value+"; path=; expires="+expireAt.toGMTString();
	}else{
		expireAt.setMonth(expireAt.getMonth() - 1);
		document.cookie="user=; path=; expires="+expireAt.toGMTString();
	}
}

function key(event){
	if (event.keyCode==13){
		ourSubmit();
	}
}

function getCookie() {
	if($.cookie('user')){
		document.getElementById("login").value = $.cookie('user');

		if($.cookie('auth')) ourSubmit();
	}
}

function ourSubmit(){
	if(document.getElementById("login").value==""){
		errDialog("Введите имя пользователя");
		document.getElementById("login").focus();
		return false;
	} else {
		if(document.getElementById("pwd").value=="" && (!$.cookie('auth'))){
			errDialog("Введите пароль");
			document.getElementById("pwd").focus();
			return false;
		}

		makeCookie();
		blockWaiting.block();
		blockWaiting.surface('Авторизация');
		document.getElementById("frm").submit();
	}
}

function errDialog(txtmsg){
	$( "#dialog:ui-dialog" ).dialog( "destroy" );
	$( "#dialog-message" ).dialog({
		modal: true,
		buttons: {
			Ok: function() {
				$( this ).dialog( "close" );
			}
		}
	});

	$(".message_dialog").text(txtmsg);
	return false;
}
