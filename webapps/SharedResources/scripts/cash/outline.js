//utf-8 құ

if(getCookie('lang') === ""){
	setCookie("lang", "RUS", 30);
	window.location.reload();
}

var docSep = "`";
var docTypeSep = "~";
var viewShowType = "";
var today = new Date();
var dd = today.getDate();
if(dd.toString().length==1){ dd = "0"+dd; }
var month = today.getMonth() + 1;
if(month.toString().length==1){ month = "0"+month; }
var todaystr = today.getFullYear() +"-"+ month +"-"+ dd;

var outline_p = {
		viewWithAdditionalShowType: {},
		type: '',
		viewid: '',
		command: null,
		curPage: 1,
		curlangOutline: '',
		keyword: '',
		commandPart: '',
		today: todaystr,
		maxPage: 0,
		sortField: 'REGDATE',
		sortOrder: 'ASC'
};

function refreshAction(){
	updateView();
}

function updateView(type, viewid, page, command, sortField, sortOrder){

	$(document).unbind("keydown");

	blockWaiting.block();

	if( typeof type==="string" )     { outline_p.type = type; }
	if( typeof viewid==="string" )   { outline_p.viewid = viewid; }
	if( typeof page==="number" )     { outline_p.curPage = page; }
	if( typeof command==="string" )  { outline_p.command = command; }

	if( typeof sortField==="string" ){
		outline_p.sortField = sortField;
		nbLocalStorage.set(outline_p.viewid+"_sortField", sortField);
	} else {
		var sf = nbLocalStorage.get(outline_p.viewid+"_sortField");
		if(sf.length){
			outline_p.sortField = sf;
		}
	}
	if( typeof sortOrder==="string" ){
		outline_p.sortOrder = sortOrder;
		nbLocalStorage.set(outline_p.viewid+"_sortOrder", sortOrder);
	} else {
		var sf = nbLocalStorage.get(outline_p.viewid+"_sortOrder");
		if(sf.length){
			outline_p.sortOrder = sf;
		}
	}

	if( typeof outline_p.command==="string" ){
		outline_p.commandPart = '&command='+outline_p.command;

		var dregxp = /command=keyword:/;
		if( dregxp.test(outline_p.commandPart) ){
			dv = outline_p.commandPart.split(':');
			outline_p.keyword = dv[1];
			outline_p.commandPart = '&keyword='+outline_p.keyword;
		}
	}

	if(outline_p.viewid in outline_p.viewWithAdditionalShowType){
		viewShowType = nbLocalStorage.get("viewShowType");
		if(viewShowType.length){
			if(outline_p.viewid.indexOf(viewShowType, (outline_p.viewid.length - viewShowType.length)) == -1){
				outline_p.viewid += viewShowType;
			}
		}
	}

	var sort = "&sortfield="+ outline_p.sortField +"&order="+ outline_p.sortOrder;

	$("#noserver").css("display","none");

	nbLocalStorage.set("viewid", outline_p.viewid, "1");
	//window.status = 'Provider?type='+outline_p.type+'&id='+outline_p.viewid+'&page='+outline_p.curPage+outline_p.commandPart+'&today='+outline_p.today+sort;

	$.ajax({
		url: 'Provider?type='+outline_p.type+'&id='+outline_p.viewid+'&page='+outline_p.curPage+outline_p.commandPart+'&today='+outline_p.today+sort,
		dataType: 'html',
		success: function(data){
			if( data.match("AuthorizationPage") ){
				window.location.href = "Provider?type=static&id=start&autologin=0";
			} else {
				if( data.length ){
					$('#view').html(data);
				} else {
					$('#view').css("display","none");
					$("#nodata").css("display","block");
				}

				blockWaiting.destroy();
			}
		},
		error: function(xhr, ajaxOptions, thrownError){
			blockWaiting.destroy();

			if( xhr.status == 400 ){
				$('#view').html(xhr.responseText);
				$("#noserver").css("display","none");
				$("#view").css("display","block");
			} else {
				$('#view').css("display","none");
				$("#noserver").css("display","block");
			}
		},
		statusCode: {
			0: function() {
				$('#view').css("display","none");
				$("#noserver").css("display","block");
			}
		}
	});
}


function openParentDocView(id, cdoctype, pos, s) {

	blockWaiting.surface("загрузка...");
	$.ajax({
		url: 'Provider?type=view&id=docthread&parentdocid='+id+'&parentdoctype='+cdoctype+'&command=expand`'+id+'`'+cdoctype+'&today='+outline_p.today,
		success: function(data) {

			if( !data.length ){
				data = '<td> </td><td colspan="5" style="color:red;">Возникла ошибка. Вернулся пустой ответ с сервера.</td>';
			} else {
				data = $(data).html();
				data = tokenizer.replace(data, "--&gt;", "<img src='/SharedResources/img/classic/arrow_blue.gif' alt='-->'/>");
			}

			data = "<tr class='response"+id+"'>"+data+"</tr>";
			$(data).insertAfter("."+ id);
	
			if($("."+id).attr("bgcolor")!==undefined){
				$("."+id+ " tr").attr("style","background:"+$("."+id).attr("bgcolor"));
			}

			blockWaiting.destroy();
		  }
		}
	);

	$("#a"+id).attr("href","javascript:closeParentDocView("+id+","+ cdoctype+","+pos+","+s+")");
	$("#img"+id).attr("src","/SharedResources/img/classic/minus.gif");
}

function closeParentDocView(id, cdoctype, pos, s){

	$.get('Provider?type=view&id=docthread&command=collaps`'+id+'`'+cdoctype+'&today='+outline_p.today, {});
	$('.response'+id).remove();

	$("#img"+id).attr("src","/SharedResources/img/classic/plus.gif");
	$("#a"+id).attr("href","javascript:openParentDocView("+id+","+ cdoctype+","+pos+","+s+")");
}


function expandChapterView(viewid,viewtext,urls) {

	$("#noserver").css("display","none");
	blockWaiting.block();

	urls = 'Provider?type=view&id='+viewid+'&command=expand`'+viewtext+'&page='+outline_p.curPage+'&today='+outline_p.today;
	$.ajax({
		url:urls,
		success: function(data) {
			$("#view").html(data);
			blockWaiting.destroy();
		}
	});	
}

function collapsChapterView(viewid,viewtext,urls) {

	$("#noserver").css("display","none");
	blockWaiting.block();

	urls = 'Provider?type=view&id='+viewid+'&command=collaps`'+viewtext+'&page='+outline_p.curPage+'&today='+outline_p.today;
	$.ajax({
		url:urls,
		success: function(data){
			$("#view").html(data);
			blockWaiting.destroy();
		}
	});	
}


function search(){
	value = $("#searchQuery").val();
	if(! value.length){
		alert('Введите строку поиска');
	}else{
		window.location.href = "Provider?type=outline&id=outline&subtype=search&keyword="+value;
	}
}


function doSearch(keyWord, num){

	if( num != undefined ){
		outline_p.curPage = num;
	}else if(! outline_p.curPage){
		outline_p.curPage = 1;
	}

	blockWaiting.block();

	$.ajax({
		url: 'Provider?type=search&keyword='+keyWord+'&page='+outline_p.curPage,
		success: function(data){
			$('#view').html(data);
			blockWaiting.destroy();
		},
		error: function(xhr, ajaxOptions, thrownError){
			blockWaiting.destroy();

			if( xhr.status == 400 ){
				$('#view').html(xhr.responseText);
				$("#noserver").css("display","none");
				$("#view").css("display","block");
			} else {
				$('#view').css("display","none");
				$("#noserver").css("display","block");
			}
		}
	});
}

function getPage(page){
	if(outline_p.type == "search"){
		doSearch(outline_p.keyword, page);
	}else{
		updateView(null, null, page, null);
	}
}

$(document).ready(function(){

	(function(){
		$('.outline a').bind("click", function(e){
			if(!e.ctrlKey){
				blockWaiting.block();
			}
		});

		$('#resizer-container').bind("click", function(){
			$("#sidebar").toggleClass("hidden");
			$('#resizer-container div').toggleClass("collapse");
		});

		$(".logout").bind("click", function(){
			logout();
		});
	})();

	if( $("#view").length ){
		if( outline_p.type == 'search' ){
			doSearch(outline_p.keyword);
		} else {
			refreshAction();
		}
	}
});


var viewcontent = {
	init: function(){
		this.view();
		this.pagination();
		this.actions();
		this.search.init();
	},
	view: function(){

		if( $(".document.notread").length && $(".document").not(".notread").length ){
			$(".showOnlyNotRead").show();
		}

		outline_p.maxPage = $("#maxpage").val();

		(function(){
			var closeddoc = nbLocalStorage.get('closeddoc');
			if( closeddoc.length ){
				var el = $("#"+closeddoc);
				try {
					$(".viewtable").animate({scrollTop: el.offset().top-200}, 300);
					flashEntry(el, 2);

					nbLocalStorage.remove("closeddoc");
				} catch(e){}
			}
		})();

		(function(){
			var _viewShowType = $("#viewShowType");
			if(_viewShowType.length){
				viewShowType = nbLocalStorage.get("viewShowType");
				if(viewShowType){
					var outline_p_viewid = "";
					if(outline_p.viewid.indexOf(viewShowType, (outline_p.viewid.length - viewShowType.length)) !== -1){
						outline_p_viewid = outline_p.viewid.substr(0, (outline_p.viewid.length - viewShowType.length));
					}
					if(outline_p_viewid in outline_p.viewWithAdditionalShowType){
						_viewShowType.attr("checked", true);
						$(".js-view-type").show();
					} else {
						_viewShowType.attr("disabled", true);
						$(".js-view-type").hide();
					}
				} else if(outline_p.viewid in outline_p.viewWithAdditionalShowType){
						$(".js-view-type").show();
				}
			}
		})();
	},
	pagination: function(maxPageControl){
		var paginationMoveKey = {37:"prev", 39:"next"};

		if(typeof maxPageControl !== "number"){
			maxPageControl = 7;
		}

		var paginationControlsContainer = ".js-view-page-nav";
		var maxPage = parseInt($("#maxpage").val(), 10);
		var curPage = parseInt($("#currentpage").val(), 10);
		var toPage = 0;

		//render pagination control
		if((maxPage>1) && ($(paginationControlsContainer).length)){
			var perPage = parseInt(maxPageControl/2, 10);
			var startPage = (curPage - perPage);
			var stopPage = (curPage + perPage);

			if(startPage <= perPage){
				startPage = 1;
			}else if(curPage == maxPage){
				startPage = maxPage - maxPageControl;
			}

			if(stopPage > (maxPage - perPage)){
				stopPage = maxPage;
			}else if(curPage == 1){
				stopPage = maxPageControl + 1;
			}

			if((maxPageControl + perPage) >= maxPage){
				startPage = 1;
				stopPage = maxPage;
			}

			try {
				var pagingControls = '<ul class="pagination">';

				if(startPage > 1){
					pagingControls += '<li class="page"><a href="#" onclick="getPage(1); return false;">' + 1 + '</a></li>';
					pagingControls += '<li class="page">...</li>';
				}

				for (var p = startPage; p <= stopPage; p++) {
					if(p==curPage){
						pagingControls += '<li class="page"><a href="#" class=" active" onclick="getPage(' + p + '); return false;">' + p + '</a></li>';
					} else {
						pagingControls += '<li class="page"><a href="#" onclick="getPage(' + p + '); return false;">' + p + '</a></li>';
					}
				}

				if(stopPage < maxPage){
					pagingControls += '<li class="page">...</li>';
					pagingControls += '<li class="page"><a href="#" onclick="getPage(' + maxPage + '); return false;">' + maxPage + '</a></li>';
				}

				if((startPage > 1) || (stopPage < maxPage)){
					pagingControls += '<li class="page"><div class="gotopage">';
					pagingControls += 'Перейти на страницу <input type="number" min="1" max="' + maxPage + '" id="goToPage" name="goToPage" value="" />';
					pagingControls += '</div></li>';
				}

				pagingControls += '</ul>';

				$(paginationControlsContainer).html(pagingControls);

				$("#goToPage").click(function(){
					$(".gotopage").css("display", "block");
				}).blur(function(){
					$(".gotopage").css("display", "");
				}).keydown(function(e){
					if(e.keyCode==13){
						var goToPage = parseInt($(this).val(), 10);
						if((goToPage>=0) && (goToPage<=maxPage)){
							getPage(goToPage);
						}else{
							window.status = "error page number";
						}
					}
				});
			} catch(e) {
				alert(e);
			}
		}


		$(document).keydown(function(e){
			if(e.ctrlKey && (e.keyCode in paginationMoveKey)){
				e.stopPropagation();
				e.preventDefault();

				try {
					switch(paginationMoveKey[e.keyCode]){
						case "prev":
							if( curPage > 1 ){
								toPage = curPage - 1;
							}
							window.status = "go to prev page: " + toPage;

							getPage(toPage);
						break;
						case "next":
							toPage = ( curPage < maxPage ) ? (curPage + 1) : 1;
							window.status = "go to next page: " + toPage;

							getPage(toPage);
						break;
					}
				}catch(e){ alert(e); }

				//$(this).unbind("keydown");
			}
		});
	},
	actions: function(){

		quickkeyevent();

		try{
			$(':checkbox').bind("click", function(){
				selfchb = $(this);
				selfchbname = selfchb.attr('name');
				allcheckbox = $("[name='"+selfchbname+"'].all");

				if( ! allcheckbox.length ){
					return true;
				}

				if( selfchb.hasClass("all") ){
					$(':checkbox[name="'+selfchbname+"']:visible").not(":disabled").attr('checked', selfchb.is(':checked'));
				}

				$coll = $("input[name='"+selfchbname+"'][value!='']:checked:visible");

				var checkedcount = $coll.length;
				if( checkedcount > 0 ){
					allcheckbox.attr("checked", checkedcount == $("input[name='"+selfchbname+"'][value!='']:visible").not(":disabled").length);

					if( $("#checkedDocsCount").length ){
						$("#checkedDocsCount").html(checkedcount);
					} else {
						var elpos = allcheckbox.position();
						var eltop = elpos.top - 3;
						var elleft = elpos.left + allcheckbox.width() + 5;
						allcheckbox.before("<span id=\"checkedDocsCount\" style=\"z-index:999;position:absolute;top:"+eltop+"px;left:"+elleft+"px;background-color:#FFFFE1;border:1px solid #CCC;padding:3px;font-size:11px;font-weight:bold;\">"+checkedcount+"</span>");
					}
				} else {
					allcheckbox.attr("checked", false);
					$("#checkedDocsCount").remove();
				}
			});
		}catch(e){}

		$('a.link, a.newdoc, a.doclink').bind("click", function(e){
			if( ! e.ctrlKey){
				blockWaiting.block();
			}
			return true;
		});

		$(".document").bind('dblclick', function(e){
			if(e.target.nodeName=="INPUT" || e.target.nodeName=="IMG"){
				return;
			}

			e.preventDefault();
			e.stopPropagation();

			doclink = $(this).find("a.link").first().attr("href");
			if (doclink.length > 0){
				if( ! e.ctrlKey){
					blockWaiting.block();
					window.location = doclink;
				} else {
					window.open(doclink);
				}
			}
		});

		$('.vaShowOnlyNotRead').bind("click", function(){
			$coll = $(".viewtable tr").not(".notread");

			if( $(this).attr("checked") ){
				$coll.hide();
				window.status = "Не прочитанных "+$(".viewtable tr:visible").size();
				//$(this).after("<span id=\"notreadcount\">"+$(".viewtable tr:visible").size()+" </span>");
			} else {
				$coll.show();
				//$("#notreadcount").remove();
			}
		});

		$('.vaDeleteDoc').bind("click", function(){
			$coll = $("input[name='chbox'][value!='']:checked:visible");
			if( $coll.length > 0 ){
				if(confirm('Удалить '+$coll.length+' документ(ов)?')){
					blockWaiting.block();

					var _keys;
					var docKeysArr = [];
					$coll.each(function(){
						_keys = $(this).val().split(":").join(docTypeSep);
						docKeysArr.push(_keys);
					});

					if(docKeysArr.length){
						_keys = docKeysArr.join(docSep);
						delDocument(_keys);
					}

					docKeysArr = null;
					_keys = null;

					setTimeout(refreshAction, 1500);
				}
			} else {
				alert("Для удаления выберите один документ");
			}
		});

		$('#viewShowType').bind("click", function(e){
			var _viewShowType = $("#viewShowType");
			viewShowType = nbLocalStorage.get("viewShowType");

			if(outline_p.viewid.indexOf(viewShowType, (outline_p.viewid.length - viewShowType.length)) !== -1){
				outline_p.viewid = outline_p.viewid.substr(0, (outline_p.viewid.length - viewShowType.length));

				if(_viewShowType.is(":checked")){
					nbLocalStorage.set("viewShowType", _viewShowType.val());
					outline_p.curPage = 0;
				} else {
					nbLocalStorage.remove("viewShowType");
					outline_p.curPage = 1;
				}
			}

			refreshAction();
		});
	},
	search: {
		init: function(){
			try {
				var value = "";

				if( ! $("#searchQuery").length ){
					return false;
				}

				viewcontent.collection = $(".document");
				if( ! viewcontent.collection.length ){
					return false;
				}

				$("#searchQuery").bind("keyup", function(){
					value = tokenizer.trim($(this).val());

					try {
						clearTimeout(to);
					} catch(e){}

					to = setTimeout(function(){ viewcontent.search.start(value); }, 300);
				}).bind("keydown", function(e){
					if(e.keyCode==13){
						search();
					}
				});
			}catch(e){ alert(e); }
		},
		start: function(value){
			try {
				if( value.length >= 2 ){
					if( viewcontent.collection.length<=50 ){
						viewcontent.search.filter(viewcontent.collection, value);
					}else{
						$(viewcontent.collection).show();
						$(viewcontent.collection).not(":contains('"+value+"')").hide();
					}
				} else {
					$(viewcontent.collection).show();
				}
			}catch(e){ alert(e); }
		},
		filter: function(coll, value){
			re = new RegExp(value, "gim");

			coll.each(function(){
				el = $(this);
				if( ! re.test(el.text()) ){
					el.hide();
				} else {
					el.show();
				}
			});
		}
	}
};
