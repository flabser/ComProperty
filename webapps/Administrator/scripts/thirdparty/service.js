
function requetsService(value){
		
		var pars = 'type='+value+'&'+Math.random();
		var myAjax = new Ajax.Updater('view', 'Service', {method: 'get', parameters: pars});
}	