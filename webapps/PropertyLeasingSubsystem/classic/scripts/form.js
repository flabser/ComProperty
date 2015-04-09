var lang = "RUS";
var fieldIsValid = true;
var alertmessage = '';
/* переменные для перевода диалогов */
var acquaintcaption = "Ознакомить"; 
var remindcaption = "Напомнить"; 
var cancelcaption = "Отмена"; 
var changeviewcaption = "Изменить вид"; 
var receiverslistcaption = "Список получателей напоминания"; 
var correspforacquaintance = "Список корреспондентов для ознакомления"; 
var searchcaption = "Поиск"; 
var commentcaption = "Комментарий"; 
var documentsavedcaption = "Документ сохранен"; 
var documentmarkread = "Документ отмечен как прочтенный"; 
var pleasewaitdocsave = "Пожалуйста ждите... Идет сохранение документа"; 
var showauthor ="Укажите кто будет подписывать проект служебной записки";
var showrecipient ="Укажите получателя";
var noblockcoord ="Вы не создали ни одного блока согласования";
var choosevalue ="Выберите значение";
var alreadychosen ="Данный корреспондент уже выбран для согласования";
var isrecieverofsz="Выбранный вами сотрудник является получателем служебной записки";
var issignerofsz="Выбранный вами сотрудник является подписывающим служебной записки";
var saving ="Сохранение"
var showfilename ="Укажите имя файла для вложения"
var addcomment ="Добавить комментарий к вложению?";
var removedfromcontrol = "Документ снят с контроля";
var warning ="Предупреждение";
var attention ="Внимание";
var addcommentforattachment ="Добавить комментарий к вложению?";
var addcomment= "Добавить комментарий";
var delete_file ="Удалить файл";
var onlynumber = "Поле может содержать только числовые значения";

function suggestionStreet() {
	var availableTags = [];
	$("#input_street").keyup(function(eventObject){
		if (eventObject.which != 37 && eventObject.which != 38 && eventObject.which != 39 && eventObject.which != 40 && eventObject.which != 13){
			availableTags.length = 0;
			if ($("#input_street").val().length > 1){
				$("#input_street").addClass("ui-autocomplete-loading");
				$("#tiptip_holder").css("display","none");
				$.ajax({
					type: "POST",
					url: 'Provider',
					data: 'type=page&id=street-sugg&'+$.param($("input[name=input_street]")),
					datatype:'xml',
					success: function(data) {
						$(data).find("entry").each(function(index, element){
							availableTags.push({label:$(element).find("viewtext").text(), value:$(element).find("viewtext").text(),id:$(element).attr("docid")});
						});
					},
					complete:function() {
						$("#input_street").autocomplete({
							source: availableTags,
							select: function(event, ui) { 
								$("#street").val(ui.item.id);
								$("#streetviewtext").val(ui.item.value);
							}
						});
						$("#input_street").autocomplete("search" , $("#input_street").val());
						availableTags = [];
					}
				});	
			}else{
				//$("#tiptip_holder").css("display","block");
			}
		}
	});
}

function addRent(docid){
    enableblockform()
    $("#cost").focus()
    divhtml ="<div id='dialog-message-comment' title='Укажите сумму арендной платы'>";
    divhtml +="Сумма(тг):<br/><input name='cost' id='cost' rows='10' onkeydown='numericfield(this)' tabindex='1' style='width:97%'/><br/>";
    divhtml +="Примечание:<br/><textarea name='commentText' id='commentText' rows='10' tabindex='1' style='width:97%'/>";
    divhtml+="</div>";
    $("body").append(divhtml);
    $("#dialog-message-comment").dialog("destroy");
    if($.cookie("lang")=="ENG"){
        $("#dialog-message-comment").dialog({
            width: 400,
            buttons:{
                OK: function() {
                    giveToRent(docid);
                    $("#dialog-message-comment").remove();

                },
                Cancel: function() {
                    $("#dialog-message-comment").remove();
                    disableblockform();
                }
            },
            beforeClose: function() {
                $("#dialog-message-comment").remove();
                disableblockform();
                hotkeysnav()
            }
        });
    }
    else if ($.cookie("lang")=="RUS" || !$.cookie("lang")){
        $("#dialog-message-comment").dialog({
            width: 400,
            buttons: {
                OK: function() {
                    giveToRent(docid);
                    $("#dialog-message-comment").remove();
                },
                Отмена: function() {
                    $("#dialog-message-comment").remove();
                    disableblockform();
                }
            },
            beforeClose: function() {
                $("#dialog-message-comment").remove();
                disableblockform();
                hotkeysnav()
            }
        });
    }
    else if ($.cookie("lang")=="KAZ"){
        $("#dialog-message-comment").dialog({
            width: 400,
            buttons: {
                OK: function() {
                    giveToRent(docid);
                    $("#dialog-message-comment").remove();
                },
                Болдырмау: function() {
                    $("#dialog-message-comment").remove();
                    disableblockform();
                }
            },
            beforeClose: function() {
                $("#dialog-message-comment").remove();
                disableblockform();
                hotkeysnav()
            }
        });
    }
    $("#commentBox textarea").focus()
}

function giveToRent(docid){
    $.ajax({
        type: "get",
        url: 'Provider?type=page&id=addrent&docid='+docid+'&cost='+$("#cost").val()+'&comment='+$("#commentText").val(),
        success:function (data){
            infoDialog("Объект объявлен в аренду");
        },
        error:function (xhr, ajaxOptions, thrownError){
            if (xhr.status == 400){
                $("body").children().wrapAll("<div id='doerrorcontent' style='display:none'></div>")
                $("body").append("<div id='errordata'>"+xhr.responseText+"</div>")
                $("li[type='square'] > a").attr("href","javascript:backtocontent()")
            }
        }
    });
}

function reviewRentApplication(docid, decision, apptype){
    enableblockform()
    if(decision == "approved")
        divhtml ="<div id='dialog-message-comment' title='Подтвердить заявление на аренду'>";
    else
        divhtml ="<div id='dialog-message-comment' title='Отклонить заявление на аренду'>";

    divhtml +="Примечание:<br/><textarea name='commentText' id='commentText' rows='10' tabindex='1' style='width:97%'/>";
    divhtml+="</div>";
    $("body").append(divhtml);
    $("#dialog-message-comment").dialog("destroy");
    if($.cookie("lang")=="ENG"){
        $("#dialog-message-comment").dialog({
            width: 400,
            buttons:{
                OK: function() {
                    setDecisionToRentApplication(docid, decision, apptype);
                    $("#dialog-message-comment").remove();

                },
                Cancel: function() {
                    $("#dialog-message-comment").remove();
                    disableblockform();
                }
            },
            beforeClose: function() {
                $("#dialog-message-comment").remove();
                disableblockform();
                hotkeysnav()
            }
        });
    }
    else if ($.cookie("lang")=="RUS" || !$.cookie("lang")){
        $("#dialog-message-comment").dialog({
            width: 400,
            buttons: {
                OK: function() {
                    setDecisionToRentApplication(docid, decision, apptype);
                    $("#dialog-message-comment").remove();
                },
                Отмена: function() {
                    $("#dialog-message-comment").remove();
                    disableblockform();
                }
            },
            beforeClose: function() {
                $("#dialog-message-comment").remove();
                disableblockform();
                hotkeysnav()
            }
        });
    }
    else if ($.cookie("lang")=="KAZ"){
        $("#dialog-message-comment").dialog({
            width: 400,
            buttons: {
                OK: function() {
                    setDecisionToRentApplication(docid, decision, apptype);
                    $("#dialog-message-comment").remove();
                },
                Болдырмау: function() {
                    $("#dialog-message-comment").remove();
                    disableblockform();
                }
            },
            beforeClose: function() {
                $("#dialog-message-comment").remove();
                disableblockform();
                hotkeysnav()
            }
        });
    }
    $("#commentBox textarea").focus()
}

function setDecisionToRentApplication(docid, decision, apptype){
    $.ajax({
        type: "get",
        url: 'Provider?type=page&id='+apptype+'_application_review&docid='+docid+'&decision='+decision+'&comment='+$("#commentText").val(),
        success:function (data){
            if(decision == "approved")
                infoDialog("Заявление подтверждено", $(data).find("redirect").text());
            else
                infoDialog("Заявление отклонено", $(data).find("redirect").text());
        },
        error:function (xhr, ajaxOptions, thrownError){
            if (xhr.status == 400){
                $("body").children().wrapAll("<div id='doerrorcontent' style='display:none'></div>")
                $("body").append("<div id='errordata'>"+xhr.responseText+"</div>")
                $("li[type='square'] > a").attr("href","javascript:backtocontent()")
            }
        }
    });
}

function sentNotify(docid){
	$.ajax({
		type: "get",
		url: 'Provider?type=page&id=sentnotification_enddatecontract&docid='+docid,
		success:function (data){
			infoDialog("Уведомление отправлено")
		},
		error:function (xhr, ajaxOptions, thrownError){
            if (xhr.status == 400){
         	  $("body").children().wrapAll("<div id='doerrorcontent' style='display:none'/>")
         	  $("body").append("<div id='errordata'>"+xhr.responseText+"</div>")
         	  $("li[type='square'] > a").attr("href","javascript:backtocontent()")
            }
         }  
	});
}

function validationyearfield(element){
	if ((/^18\d\d$|^19\d\d$|^20[0-9][0-9]$/.test($(element).val()) && $(element).val() < new Date().getYear() + 1901) || $(element).val().length == '0'){
		$("#btnsavedoc").removeAttr("disabled").removeClass("ui-state-disabled")
		$(element).removeClass("invalid")
	}else{
		$(element).blur().focus().addClass("invalid")
		$("#btnsavedoc").attr("disabled","disabled").addClass("ui-state-disabled")
	}
}

function changeCustomField(name,value){
	$("input[name="+name+"]").val(value);
}

function setValHiddenFields(el){
	$("[name=parentdocid]").val($(el).val());
}

function sentNotifyRentPayment(docid){
	$.ajax({
		type: "get",
		url: 'Provider?type=page&id=sentnotification_rentpayment&docid='+docid,
		success:function (data){
			infoDialog("Уведомление отправлено")
		},
		error:function (xhr, ajaxOptions, thrownError){
            if (xhr.status == 400){
         	  $("body").children().wrapAll("<div id='doerrorcontent' style='display:none'/>")
         	  $("body").append("<div id='errordata'>"+xhr.responseText+"</div>")
         	  $("li[type='square'] > a").attr("href","javascript:backtocontent()")
            }
         }  
	});
}

function endContract(docid){
	enableblockform()
	divhtml ="<div id='dialog-message-comment' title='Укажите причину расторжения договора'>";
	divhtml +="<textarea name='commentText' id='commentText' rows='10' tabindex='1' style='width:97%'/>";
	divhtml+="</div>";
	$("body").append(divhtml);
	$("#dialog-message-comment").dialog("destroy");
	if($.cookie("lang")=="ENG"){
		$("#dialog-message-comment").dialog({ 
			width: 400,
			buttons:{ 
				OK: function() {
					sendEndContractNotify(docid,$("#commentText").val());
					$("#dialog-message-comment").remove();
				},
				Cancel: function() {
					$("#dialog-message-comment").remove();
					disableblockform();
				}
			},
			beforeClose: function() { 
				$("#dialog-message-comment").remove();
				disableblockform();
				hotkeysnav() 
			} 
		});
	}
	else if ($.cookie("lang")=="RUS" || !$.cookie("lang")){
		$("#dialog-message-comment").dialog({ 
			width: 400,
			buttons: { 
				OK: function() {
					sendEndContractNotify(docid,$("#commentText").val());
					$("#dialog-message-comment").remove();
				},
				Отмена: function() {
					$("#dialog-message-comment").remove();
					disableblockform();
				}
			},
			beforeClose: function() { 
				$("#dialog-message-comment").remove();
				disableblockform();
				hotkeysnav() 
			} 
		});
		}
	else if ($.cookie("lang")=="KAZ"){
		$("#dialog-message-comment").dialog({ 
			width: 400,
			buttons: { 
				OK: function() {
					sendEndContractNotify(docid,$("#commentText").val());
					$("#dialog-message-comment").remove();
				},
				Болдырмау: function() {
					$("#dialog-message-comment").remove();
					disableblockform();
				}
			},
			beforeClose: function() { 
				$("#dialog-message-comment").remove();
				disableblockform();
				hotkeysnav() 
			} 
		});
		}
	$("#commentBox textarea").focus()
}

function sendEndContractNotify(docid, reason){
	$.ajax({
		type: "get",
		url: 'Provider?type=page&id=sentnotification_endcontract&docid='+docid+'&reason='+reason,
		success:function (data){
			infoDialog("Уведомление отправлено")
			disableblockform()
		},
		error:function (xhr, ajaxOptions, thrownError){
			if (xhr.status == 400){
				$("body").children().wrapAll("<div id='doerrorcontent' style='display:none'/>")
				$("body").append("<div id='errordata'>"+xhr.responseText+"</div>")
				$("li[type='square'] > a").attr("href","javascript:backtocontent()")
			}
		}  
	});
}

var quickanswer = null ;
var prevvalanswer = null ;
var preview = null ;
function addquickanswer(targetid , val, button){
	if (!$(button).hasClass("inited")){
		$(".inited").removeClass("inited")
		$(button).addClass("inited")
		if (preview == false){
			$("#"+targetid).val($("#"+targetid).val() +" " + val);
		}
		preview = false
	}else{
		$("#"+targetid).val(prevvalanswer)
		$(".inited").removeClass("inited")
	}
}

function previewquickanswer(targetid , val, button){
	if (!$(button).hasClass("inited")){
		if (prevvalanswer == null){
			prevvalanswer = $("#"+targetid).val();
			$("#"+targetid).val($("#"+targetid).val() +" " + val);
		}else{
			$("#"+targetid).val(prevvalanswer +" " + val);
		}
		preview = true
	}
}

function endpreviewquickanswer(targetid , val, button){
	if (!$(button).hasClass("inited")){
		if (prevvalanswer == null){
			$("#"+targetid).val($("#"+targetid).val());
		}else{
			$("#"+targetid).val(prevvalanswer);
		}
	}
}

function resetquickanswerbutton(){
	$(".inited").removeClass("inited");
	quickanswer = null;
	prevvalanswer = null;
	preview = null;
}

function changeRentHours(){
	var endcontractdate=new Date($.datepicker.parseDate( "dd.mm.yy", $("#startcontractdate").val()));
	var endrentdate=new Date($.datepicker.parseDate( "dd.mm.yy", $("#endrentdate").val()));
	endrentmonth =endrentdate.getMonth()
	endrentyear =endrentdate.getYear()
	var days_in_month = 32 - new Date(endrentyear, endrentmonth, 32).getDate();
	currenttarif = $("#currenttarif").val()
	$("input[name=tarif]").val(Math.floor((($("#"+currenttarif).val() / days_in_month ) / 24) * $("select[name=sa]").val()*100)/100)
}

function changeRentHoursN(){
	var endcontractdate=new Date($.datepicker.parseDate( "dd.mm.yy", $("#startcontractdate").val()));
	var endrentdate=new Date($.datepicker.parseDate( "dd.mm.yy", $("#endrentdate").val()));
	endrentmonth =endrentdate.getMonth()
	endrentyear =endrentdate.getYear()
	var days_in_month = 32 - new Date(endrentyear, endrentmonth, 32).getDate();
	currenttarif = $("#currenttarif").val()
	$("input[name=tarif]").val(Math.floor((1731 * 0.1) * $("select[name=sa]").val()*100)/100)
}

function changeRentMonths(){
	currenttarif = $("#currenttarif").val()
	$("input[name=tarif]").val($("#"+currenttarif).val() * $("select[name=sa]").val())
}

function changeRentMonthsN(){
	$("input[name=tarif]").val($("#starttarif").val() * $("select[name=sa]").val())
}

function loadN(){
	$.ajax({
		type: "get",
		url: 'Provider?type=page&id=all&onlyxml',
		success:function (data){
			$(data).find("view_content").find("entry").each(function(){
				$("#nanimatel").append("<option value='"+ $(this).attr("docid")+"'>"+$(this).find("viewtext").text()+"</option>")
			})
			
		},
		error:function (xhr, ajaxOptions, thrownError){
            if (xhr.status == 400){
         	  $("body").children().wrapAll("<div id='doerrorcontent' style='display:none'></div>")
         	  $("body").append("<div id='errordata'>"+xhr.responseText+"</div>")
         	  $("li[type='square'] > a").attr("href","javascript:backtocontent()")
            }
         }  
	});
}

$.fn.extend({
    notify : function(options) {
    	var defaults = { 
    		text: "",
    	  	onopen: function(){$("body").hidenotify()},
    	  	loadanimation:true
    	};
    	var opts = $.extend(defaults, options);
    	$(this).append("<div id='notifydiv'><font>"+opts.text+"</font></div>")
    	if (opts.loadanimation){
    		$("#notifydiv").append("<img src='classic/img/26.png' style='position:absolute; top:3px; right:10px'/>")
    	}
    	$("#notifydiv").animate({top: '0px'},'fast',opts.onopen);
    },
    hidenotify : function(options) {
    	var defaults = { 
        	delay: "1500",
        	onclose: function(){}
        };
        var opts = $.extend(defaults, options);
    	setTimeout(function() {
    		$("#notifydiv").animate({top: '-40px'},'fast',opts.onclose);
        }, opts.delay);
    }
});

function enabledChbox (name){
	$("input[name="+name+"]").attr("checked","true");
}

/* ограничение количества ввода символов в поле*/
function maxCountSymbols (el, count, e, warn){
	$(el).keypress(function (e) {
		if ($(el).val().length > count){
			if ((e.which == 8) ||  (e.which > 36 && e.which < 41) ) {
				if (e.which == 8 && $("#warntext").is(":visible")){
					$("#warntext").remove()
				}
			}else{
				if(!$("#warntext").is(":visible") && warn==true){
					$(el).parent().append("<font id='warntext' style='color:red; font-size:11px; margin-left:5px; line-height:20px'>Длина поля не должна превышать "+count+" символов</font>")
				}
				return false
			}
		}
	});
}

/*проверка выбранного формата файла отчета*/
function reportsTypeCheck(el){
	if($(el).val() == 2){
		$("input[type=radio][name=disposition][value=inline]").attr("disabled","disabled")
		$("input[type=radio][name=disposition][value=attachment]").attr("checked","checked")
	}else{
		$("input[type=radio][name=disposition][value=inline]").removeAttr("disabled")
	}
}

function fillingReport(){
	loadingOutline()
	enableblockform()
	$("body").css("cursor","wait")
	var fields = $('form').serializeArray();
	var formData = $("form").serialize();
	var recursiveEncoded = $.param(fields);
	window.location.href = 'Provider?type=handler&id='+$("input[name=id]").val()+'&'+recursiveEncoded;
	$('#loadingpage , #blockWindow').css("display","none");
	$("body").css("cursor","default")
	disableblockform()
}

/* кнопка "назад"*/
function CancelForm(url,grandparform) {
	url != null && url.length !=0 ? window.location.href=url : window.history.back();
}

function fieldOnFocus(field) {
	field.style.backgroundColor = '#FFFFE4';
}

function fieldOnBlur(field) {
	field.style.backgroundColor = '#FFFFFF';
}

function numericfield(el) {
	$(el).keypress(function (e) {
		if ((e.which < 48) || (e.which > 57) ) {
			if( (e.which == 8 || e.which == 13 ) ){
				if ($(el).val().length == 1){
					$("#tiptip_holder").css("display","block")
				}
			}else{
				$("#tiptip_holder").css("display","block")
				return false
			}
		}else{
			$("#tiptip_holder").css("display","none")
		}
	});
	
}

function infoDialog(text, redirecturl){
	$(document).unbind("keydown")
	divhtml ="<div id='dialog-message' title='"+warning+"'>";
	divhtml +="<span style='height:40px; width:100%; text-align:center;'>"+
	"<font style='font-size:13px;'>"+text+"</font></span>";
	divhtml += "</div>";
	$("body").append(divhtml);
	$("#dialog-message").dialog("destroy");
	$("#dialog-message").dialog({
		modal: true,
		buttons: {
			"Ок": function() {
				$(this).dialog("close").remove();
				$(".hotnav-accesskey").remove()
				hotkeysnav()
                redirecturl != undefined ? window.location = redirecturl : window.location.reload();
			}
		},
		 beforeClose: function() { 
			 $("#dialog-message, .hotnav-accesskey").remove();
			 hotkeysnav()  
		} 
	});
	$(".ui-dialog button").focus();
}


function dialogAndFunction(text,func, name, par){
	divhtml ="<div id='dialog-message' title='Предупреждение'>";
	divhtml +="<span style='text-align:center;'>"+
		"<font style='font-size:13px;'>"+text+"</font></span></div>";
	$("body").append(divhtml);
	$("#dialog-message").dialog("destroy");
	$("#dialog-message").dialog({
		modal: true,
		buttons: {
			"Ок": function() {
				func && typeof(func) === "function" ? func() : $(this).dialog("close").remove();
			}
		},
		beforeClose: function() { 
			func && typeof(func) === "function" ? func() : $(this).dialog("close").remove();
		}
	});
}
 
function dialogConfirmComment (text,action){
	divhtml ="<div id='dialog-message' title='"+warning+"'>";
	divhtml+="<span style='height:50px; margin-top:4%; width:100%; text-align:center'>"+
	"<font style='font-size:13px; '>"+text+"</font></span></div>";
	$("body").append(divhtml);
	$("#dialog-message").dialog("destroy");
	$("#dialog-message").dialog({
		height:140,
		modal: true,
		buttons: {
			"да": function() {
				$(this).dialog("close").remove();
		    	addComment(action)
			},
			"нет": function() {
				$(this).dialog("close").remove();
				submitFormDecision (action)
			}
		},
		beforeClose: function() { 
			$("#dialog-message").remove();
			disableblockform();
			hotkeysnav() 
		} 
	});
	if (lang == "KAZ"){
		$("#dialog-message").dialog({ buttons: { 
			"иә": function() { 
				$(this).dialog("close").remove();
					addComment(action)
				},
			"жоқ": function() { 
				$(this).dialog("close").remove();
					submitFormDecision (action)
				}
			}
		});
	}
	if (lang == "ENG"){
		$("#dialog-message").dialog({ buttons: { 
			"yes": function() { 
				$(this).dialog("close").remove();
				addComment(action)
			},
			"no": function() { 
				$(this).dialog("close").remove();
				submitFormDecision(action)
			}
		}
		});
	}
	if (lang == "CHN"){
		$("#dialog-message").dialog({ buttons: { 
			"是否": function() { 
				$(this).dialog("close").remove();
				addComment(action)
			},
			"無": function() { 
				$(this).dialog("close").remove();
				submitFormDecision (action)
			}
		}
		});
	}
}

function dialogConfirm (text,el,actionEl){
	divhtml ="<div id='dialog-message' title='"+warning+"'>";
	divhtml+="<span style='height:50px; margin-top:4%; width:100%; text-align:center'>"+
		"<font style='font-size:13px;'>"+text+"</font></span></div>";
	$("body").append(divhtml)
	$("#dialog-message").dialog("destroy");
	$("#dialog-message").dialog({
		height:140,
		   modal: true,
		   buttons: {
			   "Ок": function() {
				   $(this).dialog("close").remove();
				   if (el == 'picklist'){
					   $('#blockWindow').css('display',"block")
				   }
				   $('#'+el).css('display', "inline-block");
				   $("."+actionEl).remove();
			},
			"Отмена": function() {
				$(this).dialog("close").remove();
				if (el == 'picklist'){
					$('#blockWindow').remove()
				}
				$('#'+el).empty().remove();
			}
		}
	});
}

/* сохранение формы */
function SaveFormJquery(redirecturl) {
	enableblockform()
	$("body").notify({"text":pleasewaitdocsave,"onopen":function(){}})
	divhtml ="<div id='dialog-message' title="+saving+">";
	divhtml+="<div style='margin-top:8px'><font style='font-size:13px; font-family:Verdana,Arial,Helvetica,sans-serif; padding-left:10px'>Пожалуйста ждите...</font></div>";
	divhtml+="<div style='margin-top:5px'><font style='font-size:13px; font-family:Verdana,Arial,Helvetica,sans-serif; padding-left:10px'>идет сохранение документа</font></div>";
	divhtml+="<br/><div style='margin-top:5px; text-align:center;'><font style='font-size:14px; font-family:arial; padding-left:10px;'></font></div>";
	divhtml+="</div>";
	$("body").append(divhtml);
	$("#dialog").dialog("destroy");
	var formData = $("form").serialize();
	if($("#SignApplet").length !=0 ){
		sign="";
		$("#frm input[name]").each(function(){
			sign+=$(this).val()
		})
		$("#frm").append("<input type='hidden' name='srctext' value="+sign+"/><input type='hidden' name='applettype' value='sign'/>")
		taskauthor=$("input[name=taskauthor]").val()
		app=document.getElementById('SignApplet');
		$("#frm").append("<input type='hidden' name='signedfields' value='"+app.getSign(sign,taskauthor)+"'/>")
	}
	$.ajax({
		type: "POST",
		url: 'Provider',
		data: $("form").serialize(),
		success:function (xml){ 
			$(document).unbind("keydown")
			redir = $(xml).find('redirect').text() || redirecturl;
			$(xml).find('response').each(function(){
				var st=$(this).attr('status');
				msgtext=$(xml).find('message[id=1]').text();
				if (st =="error" || st =="undefined"){
					msgtext = msgtext || "Ошибка сохранения"
					$("#notifydiv").html("<font>"+msgtext+"</font>")
					$("body").hidenotify({"delay":800,"onclose":function(){$("#notifydiv").remove()}})
					$("#dialog-message").dialog({ 
						buttons: { 
							Ok: function() {
								$("#dialog-message").remove();
								disableblockform();
								hotkeysnav() 
							}
						},
						beforeClose: function() { 
							$("#dialog-message").remove();
							disableblockform();
							hotkeysnav() 
						} 
					});
					$("#dialog-message").html("<div style='text-align:center'><font style='font-size:13px; font-family:Verdana,Arial,Helvetica,sans-serif; padding-left:10px'>"+msgtext+"</font></div>");
				}
				if (st == "ok"){
					if (msgtext.length==0){
						$("#notifydiv").html("<font>"+documentsavedcaption+"</font>")
						setTimeout(function() {
							$("body").hidenotify({"delay":200,"onclose":function(){if (redir == ''){window.history.back()}else{window.location = redir;}}})
						},250);
					}else{
						$("#notifydiv").html("<font>"+msgtext+"</font>")
						setTimeout(function() {
							$("body").hidenotify({"delay":300,"onclose":function(){}})
						},800);
						$("#dialog-message").dialog({ 
							buttons: { 
								Ok: function() {
									$("#dialog-message").remove();
									redir == '' ? window.history.back() : window.location = redir;
									disableblockform();
								}
							},
							beforeClose: function() { 
								$("#dialog-message").remove();
								redir == '' ? window.history.back() : window.location = redir;
								disableblockform();
							}  
						});
						$("#dialog-message").html("<br/><div style='text-align:center'><font style='font-size:13px; font-family:Verdana,Arial,Helvetica,sans-serif; padding-left:10px'>"+msgtext+"</font></div>");
						$(".ui-dialog button").focus();
					}
				}
				
			});
		},
		error:function (xhr, ajaxOptions, thrownError){
			if (xhr.status == 400){
				$("body").children().wrapAll("<div id='doerrorcontent' style='display:none'></div>")
				$("body").append("<div id='errordata'>"+xhr.responseText+"</div>")
				$("li[type='square'] > a").attr("href","javascript:backtocontent()")
			}
		}    
	});
}

/*set of upload function*/
function loadingAttch(tableID){
	$("#"+tableID).append("<tr id='loading_attach'><td></td><td><div style='position:absolute; z-index:999'><img src='/SharedResources/img/classic/progress_bar_attach.gif'></div></td></tr>")
	blockWindow = "<div class='blockWindow' id='blockWindow'/>"; 
	$("body").append(blockWindow).css("cursor","wait");
	$('#blockWindow').css('width',$(document).width()).css('height',$(document).height()).css('display',"block"); 
}

/* добавление вложений в форму */
function submitFile(formid, tableID, fieldName) {
	if ($('input[name='+fieldName+']').val() == '' || $('input[name='+fieldName+']').val() == 'undefined') {
		infoDialog(showfilename);
	}else{
		form = $('#'+formid);
		$(form).find("#progressbar").progressbar({value:0});
		$(form).find("#progressstate").css("display","block")
		var frame = createIFrame();
		frame.onSendComplete = function() {
			uploadComplete(tableID, getIFrameXML(frame));
			$("#loading_attach, #loadingpage, #blockWindow ").remove();
			$("body").css("cursor","default")
		};
		form.attr('target',frame.id);
		form.submit();
	}
}

var req;
function ajaxFunction(){
	req = $.get("Uploader",processStateChange);
}


var cancel_button_action;
function processStateChange(){
	if (req.readyState == 4){
		if (req.status == 200){ 
			$("#loading_attach_img").css("visibility","visible")
			$(".button_panel").find("button:not(#canceldoc)").attr("disabled","disabled").addClass("ui-state-disabled")
			if($("#canceldoc").attr("onclick") != "javascript:confirmCancelAttach()"){
				cancel_button_action= $("#canceldoc").attr("onclick");
				$("#canceldoc").attr("onclick","javascript:confirmCancelAttach()")
			}
			var xml = req.responseXML,
				isNotFinished = $(xml).find("finished")[0],
				myBytesRead = $(xml).find("bytes_read")[0],
				myContentLength = $(xml).find("content_length")[0],
				myPercent = $(xml).find("percent_complete")[0];
			if((isNotFinished == null) && (myPercent == null)){
				$("#initializing").css("visibility","visible")
				window.setTimeout("ajaxFunction();", 150);
			}else{
				$("#readybytes, #percentready").css("visibility","visible")
				$("#initializing").css("visibility","hidden")
				kbContentLength = parseInt($(myContentLength).text())/1024;
				mbContentLength = parseInt(kbContentLength)/1024;
				if (myPercent != null){
					$("#progressbar").progressbar( "option", "value",parseInt($(myPercent).text()));
					kbread = parseInt($(myBytesRead).text())/1024;
					mbread = parseInt(kbread)/1024
					$("#readybytes").html(Math.round(mbread * 10 ) / 10  + " из " + Math.round(mbContentLength * 10) / 10   + " мбайт загружено")
					$("#percentready").html($(myPercent).text() + "%")
					window.setTimeout("ajaxFunction();", 100);
				}else{
					$(".button_panel").find("button:not(#canceldoc)").removeAttr("disabled").removeClass("ui-state-disabled")
					$("#dialog-message-cancel-attach").dialog('close').remove();
					$("#readybytes").html(Math.round(mbContentLength * 10)/10   + " из " + Math.round(mbContentLength * 10) / 10   + " мбайт загружено")
					$("#percentready").html("готово")
					$("#loading_attach_img").css("visibility","hidden")
					$("#canceldoc").attr("onclick",cancel_button_action)
					$("#progressbar").progressbar("option", "value", 100);
				}
			}
		}else{
			alert(req.statusText);
		}
	}
}

function confirmCancelAttach(){
	divhtml ="<div id='dialog-message-cancel-attach' title='Предупреждение'>";
	divhtml+="<span style='height:40px'><p><font style='font-size:13px'>Процесс прикрепления файла к документу не закончен. Все несохраненные данные будут утеряны. Закрыть документ?</font></p></span>";
	divhtml += "</div>";
	$("body").append(divhtml);
	$("#dialog-message-cancel-attach").dialog({
		modal: true,
		height:200,
		width:460,
		buttons: {
			"Да": function(){
				$("#canceldoc").attr("onclick",cancel_button_action).click();
				$(this).dialog('close').remove();
			},
			"Нет": function(){
				$(this).dialog('close').remove();
			}
		}
	});
}

function createIFrame() {
	var id = 'f' + Math.floor(Math.random() * 99999);
	var divHTML = '<div><iframe style="display:none" src="about:blank" id="' + id
			+ '" name="' + id + '" onload="sendComplete(\'' + id + '\')"></iframe><div>';
	$("body").append(divHTML);
	return document.getElementById(id);
}

function sendComplete(id) {
	var iframe = document.getElementById(id);
	if (iframe.onSendComplete && typeof (iframe.onSendComplete) == 'function')
		iframe.onSendComplete();
}

function getIFrameXML(iframe) {
	var doc = iframe.contentDocument;
	if (!doc && iframe.contentWindow)
		doc = iframe.contentWindow.document;
	if (!doc)
		doc = window.frames[iframe.id].document;
	if (!doc)
		return null;
	if (doc.location == "about:blank")
		return null;
	if (doc.XMLDocument)
		doc = doc.XMLDocument;
	return doc;
}

var cnt = 0;
function uploadComplete(tableID, doc) {
	if (!doc)
		return;
	var xmldoc = doc.documentElement,
		st = xmldoc.getAttribute('status'),
		msg = xmldoc.getElementsByTagName('BODY'),
		fileName= $('input[name=fname]')[0].files[0].name;
	if (st == 'ok'){
		tableid='#'+tableID;
		var table = $(tableid);
		sesid=$(doc).find("message").attr('formsesid');
		csf=$(doc).find("message[id=2]").text();
		var range = 200 - 1 + 1;
		if(fileName.indexOf(".") != -1){
			detectExtAttach(fileName); //определение расширения
			fieldid=Math.floor(Math.random()*range) + 1;
			fileid=$(doc).find("message[id=4]").text();
            filehash=$(doc).find("message[id=2]").text();
            fileNameEcr=fileName.replace(/\%/g, "%25").replace(/\+/g, "%2b");// убираем знак '+' из ссылок
			$(table).append("<tr id='"+ csf + "'>" +
					'<td><input type="hidden" name="filename" value="'+fileName+'"/><input type="hidden" name="fileid" value="'+fileid+'"/><input type="hidden" name="filehash" value="'+filehash+'"/></td>' +
					"<td>" +
					"<img class='newimageatt' onerror='javascript:changeAttIcon(this)' onload='$(this).removeClass()' src='/SharedResources/img/iconset/file_extension_"+ ext +".png' style='margin-right:5px'><a href='Provider?type=getattach&formsesid="+ sesid+"&field=rtfcontent&key="+fileid+"&doctype=896&id=rtfcontent&file="+fileNameEcr+"' style='vertical-align:6px'>"+ fileName +"</a>&#xA0;&#xA0;" +
					"<a href='javascript:addCommentToAttach(&quot;"+ csf +"&quot;)' style='vertical-align:5px; '>"+
			"<img id='commentaddimg"+csf+"' src='/SharedResources/img/classic/icons/comment_add.png' style='width:12px; height:12px' title='"+addcomment+"'/></a>"+
			"<a href='javascript:deleterow(&quot;"+sesid +"&quot;,&quot;"+ fileNameEcr +"&quot;,&quot;"+ csf +"&quot;)'><img src='/SharedResources/img/iconset/cross.png' style='margin-left:5px; width:10px; height:10px; vertical-align:6px'  title='"+delete_file+"'/></a>" +
			"</td><td></td</tr>");
			$("input[name=deletertfcontentname]").each(function(index, element){
				if($(element).val() == d){
					$(element).remove()
				}
			});
			if ($("input[name=deletertfcontentname]").length == 0){
				$("input[name=deletertfcontentsesid], input[name=deletertfcontentfield]").remove()
			}
			$("#progressbar").progressbar("destroy");
			$("#progressstate").css("display","none");
			ConfirmCommentToAttach(addcommentforattachment,csf)
		}else{
			$("#progressbar").progressbar("destroy");
			infoDialog('Произошла ошибка на сервере при выгрузке файла');
		}
	}else{
		$("#progressbar").progressbar("destroy");
		infoDialog('Произошла ошибка на сервере при выгрузке файла');
	}
	$("#upload")[0].reset();
}

function changeAttIcon(el){
	$(el).attr("src","/SharedResources/img/iconset/file_extension_undefined.png");
	$(el).removeClass()
}

/*определение расширения вложения */
function detectExtAttach(file){
	var fileLen=file.length;
	var symbol;
	while(symbol !='.' || fileLen == 0){
		symbol=(file.substring(fileLen-1,fileLen));
		fileLen = fileLen - 1;
	}
	RegEx=/\s/g;
	ext=file.substring(fileLen +1, file.length).replace(RegEx, "").toLowerCase();
	return ext;
}

var control_sum_file = null; 
function ConfirmCommentToAttach(text,csf){
	control_sum_file = csf;
	divhtml ="<div id='dialog-message' title='"+attention+"'>";
	divhtml +="<span style='height:50px; margin-top:4%; width:100%; text-align:center'>"+
	"<font style='font-size:13px;'>"+text+"</font></span></div>";
	$("body").append(divhtml);
	$("#dialog").dialog("destroy");
	if ($.cookie("lang")=="RUS" || !$.cookie("lang")){
		$("#dialog-message").dialog({
			height:140,
			modal: true,
			buttons:{
				"да": function() {
					$(this).dialog("close").remove();
					addCommentToAttach()
				},
				"нет": function() {
					$(this).dialog("close").remove();
					$("<tr><td colspan='3'></td></tr>").insertAfter("#"+control_sum_file);
				}
			},
			beforeClose: function() { 
				$("<tr><td colspan='3'/></tr>").insertAfter("#"+control_sum_file);
				$("#progressbar").progressbar("destroy");
				$("#progressstate").css("display","none");
				$("#readybytes, #percentready, initializing").text('')
			} 
		});
	}
	else if  ($.cookie("lang")=="ENG"){
		$("#dialog-message").dialog({
			height:140,
			modal: true,
			buttons:{
				"yes": function() {
					$(this).dialog("close").remove();
					addCommentToAttach()
				},
				"no": function() {
					$(this).dialog("close").remove();
					$("<tr><td colspan='3'></td></tr>").insertAfter("#"+control_sum_file);
				}
			},
			beforeClose: function() { 
				$("<tr><td colspan='3'></td></tr>").insertAfter("#"+control_sum_file);
				$("#progressbar").progressbar("destroy");
				$("#progressstate").css("display","none");
				$("#readybytes, #percentready , initializing").text('')
			} 
		});
		}
	else if  ($.cookie("lang")=="KAZ"){
		$( "#dialog-message" ).dialog({
			height:140,
			modal: true,
			buttons:{
				"иә": function() {
					$(this).dialog("close").remove();
					addCommentToAttach()
				},
				"жоқ": function() {
					$(this).dialog("close").remove();
					$("<tr><td colspan='3'></td></tr>").insertAfter("#"+control_sum_file);
				}
			},
			beforeClose: function() { 
				$("<tr><td colspan='3'></td></tr>").insertAfter("#"+control_sum_file);
				$("#progressbar").progressbar("destroy");
				$("#progressstate").css("display","none");
				$("#readybytes, #percentready , initializing").text('')
			} 
		});
	}
	else if  ($.cookie("lang")=="CHN"){
		$( "#dialog-message" ).dialog({
			height:140,
			modal: true,
			buttons:{
				是: function() {
					$(this).dialog("close").remove();
					addCommentToAttach()
				},
				不: function() {
					$(this).dialog("close").remove();
					$("<tr><td colspan='3'></td></tr>").insertAfter("#"+control_sum_file);
				}
			},
			beforeClose: function() { 
				$("<tr><td colspan='3'></td></tr>").insertAfter("#"+control_sum_file);
				$("#progressbar").progressbar("destroy");
				$("#progressstate").css("display","none");
				$("#readybytes, #percentready , initializing").text('')
			} 
		});
	}
	
	
}

function addCommentToAttach(csf){
	if (csf){
		control_sum_file = csf;
	}
	divhtml="<div class='comment' id='commentBox'>" +
	"<div class='headerComment'><font class='headertext'>"+commentcaption+"</font>" +
		"<div class='closeButton'  onclick='commentCancel();'>" +
			"<img style='width:15px; height:15px; margin-left:3px; margin-top:2px' src='/SharedResources/img/iconset/cross.png'/>" +
		"</div></div>" +
	"<div class='contentComment'>" +
		"<br/><table style=' margin-top:2%; width:100%'>" +
			"<tr>" +
				"<td style='text-align:center'><textarea  name='commentText' id='commentText' rows='10' tabindex='1' style='width:97%'/>" +
				"</td>" +
			"</tr>" +
		"</table><br/>" +
	"</div>"+
	"<div class='buttonPaneComment button_panel' style='margin-top:1%; text-align:right; width:98%'>" +
	"<button onclick='javascript:commentToAttachOk()' style='margin-right:5px'><font class='button_text'>ОК</font></button>" +
	"<button onclick='javascript:commentCancel()'><font class='button_text'>"+cancelcaption+"</font></button>" +
	"</div>" +
	"</div>";
	$("body").append(divhtml);
	$("#commentBox #button_panel").children("button").button()
	$("#commentBox").draggable({handle:"div.headerComment"});
	centring('commentBox',470,250);
	$("#commentBox textarea").focus()
}

function commentToAttachOk(){
	if ($("#commentText").val().length ==0){
		if ($.cookie("lang")=="RUS" || !$.cookie("lang"))
			infoDialog("Введите комментарий");
		else if ($.cookie("lang")=="ENG")
			infoDialog("Add comments");
		else if ($.cookie("lang")=="KAZ")
			infoDialog("Түсініктеме жазыңыз");
	}else{
		$("#frm").append("<input type='hidden' name='comment"+control_sum_file+"' value='"+ $("#commentText").val() +"'>")
		var comment_caption = "комментарий:"
		if($.cookie("lang")=="ENG")
			comment_caption = "comments:";
		else if ($.cookie("lang")=="KAZ")
			comment_caption = "түсініктеме:";
		
		$("<tr><td></td><td style='color:#777; font-size:12px'>"+comment_caption+" "+$("#commentText").val()+"</td><td></td></tr>").insertAfter("#"+control_sum_file)
		$("#commentaddimg"+control_sum_file).remove()
		$("#commentBox").remove()
	}
}

/* создание  cookie для сохранения настроек пользователя и сохранение профиля пользователя*/
function saveUserProfile(redirecturl){
		enableblockform()
		$(document).unbind("keydown")
		if ($("#newpwd").val() != $("#newpwd2").val()){
			 infoDialog("Введеные пароли не совпадают")
		}else{
		$.ajax({
			type: "POST",
			url: "Provider?type=save&element=user_profile",
			datatype:"html",
			data: $("form").serialize(),
			success: function(xml){
				redir = $(xml).find('redirect').text() || redirecturl;
				$.cookie("lang", $("select[name='lang']").val(),{ path:"/", expires:30});	
				$.cookie("refresh", $("select[name='refresh']").val(),{ path:"/", expires:30});		
				/*$.cookie("pagesize",$("select[name='countdocinview']").val(),{ path:"/", expires:30});	
				if ($("#history_vis").is(":checked")){
					historyval="history_on";
				}else{
					historyval="history_off";
				}
				$.cookie("history",historyval ,{ path:"/", expires:30 });	*/	
				redir == '' ? window.history.back() : window.location = redir;
			},
			error:function (xhr, ajaxOptions, thrownError){
               if (xhr.status == 400){
            	  if( xhr.responseText.indexOf("Old password has not match")!=-1){
            		  infoDialog("Некорректно заполнено поле 'старый пароль'")
            	  }else{
            		  $("body").children().wrapAll("<div id='doerrorcontent' style='display:none'></div>")
            		  $("body").append("<div id='errordata'>"+xhr.responseText+"</div>")
            		  $("li[type='square'] > a").attr("href","javascript:backtocontent()")
            	  }
               }
            }    
		});
	}
}

function backtocontent(){
	$('#doerrorcontent').css('display','block'); 
	$('#errordata').remove();
}

/*функция о отметке о прочтении документа*/
function markRead(doctype, docid){
	$.ajax({
		type: "GET",
		url: "Provider?type=service&operation=mark_as_read&id=mark_as_read&doctype="+doctype+"&key="+docid+"&nocache="+Math.random(),
		success:function (xml){
			$("body").notify({"text":documentmarkread,"onopen":function(){$("body").hidenotify({"delay":1200,"onclose":function(){$("#notifydiv").remove()}})},"loadanimation":false})
		}
	});
}

function deleterow(sesid,filename, fieldid){
	$("#progressbar").progressbar("destroy");
	$("#progressstate").hide();
	$("#"+fieldid).next("tr").remove()
	$("#"+fieldid).remove()
	$("#frm").append("<input type='hidden' name='deletertfcontentsesid' value='"+ sesid +"'/><input type='hidden' name='deletertfcontentname' value='"+ filename +"'/><input type='hidden' name='deletertfcontentfield' value='rtfcontent'/>")
}

var el;
var UrlDecoder = {
		encode : function (string){
			return escape(this._utf8_encode(string));
		},
		decode : function (string){
			return this._utf8_decode(unescape(string));
		},
		_utf8_encode : function (string){
			string = string.replace(/\r\n/g,"\n");
				var utftext = "";
				for (var n = 0; n < string.length; n++){
					var c = string.charCodeAt(n);
					if (c < 128){
						utftext += String.fromCharCode(c);
					}else if((c > 127) && (c < 2048)) {
						utftext += String.fromCharCode((c >> 6) | 192);
						utftext += String.fromCharCode((c & 63) | 128);
					}else{
						utftext += String.fromCharCode((c >> 12) | 224);
						utftext += String.fromCharCode(((c >> 6) & 63) | 128);
						utftext += String.fromCharCode((c & 63) | 128);
					}
				}
				return utftext;
			},
			_utf8_decode : function (utftext) {
				var string = "";
				var i = 0;
				var c = c1 = c2 = 0;
				while ( i < utftext.length ) {
					c = utftext.charCodeAt(i);
					if (c < 128) {
						string += String.fromCharCode(c);
						i++;
					}else if((c > 191) && (c < 224)) {
						c2 = utftext.charCodeAt(i+1);
						string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
						i += 2;
					}else{
						c2 = utftext.charCodeAt(i+1);
						c3 = utftext.charCodeAt(i+2);
						string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
						i += 3;
					}
				}
				string=string.substring(0, string.length )
				return string;
			}
}

/* функция для отображения списка прочитавших текущий документ */
function usersWhichRead(el,doctype, id){
	notEmpty = false;
	var ce = $(el);
	var left_offset_position = ce.offset().left ; 
	var bottom_offset_position = ce.offset().top +35; 
	$.ajax({
		type: "get",
		url: 'Provider?type=service&operation=users_which_read&id=users_which_read&doctype='+doctype+"&key="+id,
		success:function (xml){
			if (!$("#userWhichRead").length){
				$("body").append("<div id='userWhichRead' class='userwichread'></div>");
			}else{
				return false;
			}
			$(xml).find("entry").each(function(){
				if ($(this).attr('username') != undefined){
					$("#userWhichRead").append("&#xA0;"+$(this).attr('username')+ "&#xA0;&#xA0; "+$(this).attr('eventtime')+ "&#xA0;</br>");
					notEmpty = true; 
				}
			})
			if (notEmpty == true){
				$("#userWhichRead").css("right","20px").css("top",bottom_offset_position).css("display","inline-block");
			}else{
				$("#userWhichRead").remove();
			}
		}
	})
}

function usersWhichReadInTable(el,doctype, id){
	$.ajax({
		type: "get",
		async:true,
		url: 'Provider?type=service&operation=users_which_read&id=users_which_read&doctype='+doctype+"&key="+id+"&nocache="+Math.random() * 300,
		success:function (xml){
			$(xml).find("entry").each(function(){
				if ($(this).attr('username') != undefined && $("#userswhichreadtbl td:contains('"+ $(this).attr('username') +"')").length == 0 ){
					$("#userswhichreadtbl").append("<tr><td>"+$(this).attr('username')+ "</td><td>"+$(this).attr('eventtime')+ "</td></tr>");
				}
			})
		}
	})
}

function checkImage(el){
	if ($(el).width() > $("#property").width() - $(".fc").first().width()-50 ){
		$(el).css("width",$("#property").width() - $(".fc").first().width()-50 + "px")
		$(el).parent("div").css("width",$("#property").width() - $(".fc").first().width()-10 + "px")
	}
}

$(function($) {
	$.datepicker.regional['ru'] = {
		dateFormat : 'dd.mm.yy',
		firstDay : 1,
		isRTL : false,
		showMonthAfterYear : false,
		yearSuffix : ''
	};
	$.datepicker.setDefaults($.datepicker.regional['ru']);
});