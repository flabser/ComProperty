//set utf-8 құ
/*
 * Валидация полей формы. author Medet
 */

function validationFields(formID){

	var alertmessage = "";
	try {
		$v_target = null;

		if( typeof(formID) != "undefined" ){
			if( $("#"+formID).attr("id")!=formID ){
				return "Не найдена форма: "+formID;
			}

			$v_target = $("#"+formID+" [required]");
		}else{
			$v_target = $("[required]");
		}

		$v_target.each(function(){
			if( ! $(this).val().length ){
				$(this).addClass("require");
				title = ($(this).attr("title")) ? $(this).attr("title") : $(this).attr("name");
				alertmessage += title + "<br/>";
			}else{
				$(this).removeClass("require");
			}
		});
	}catch(e){
		alert(e);
		return e;
	}

	$v_target = "";
	alertmessage = (! alertmessage.length) ? "" : "<div><b>Заполните поля</b></div>" + alertmessage;

	return alertmessage;
}
