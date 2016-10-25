produtoModule.filter('nroProtocolo', function() {
    return function(value) {
        
    	if(value == undefined){
    		return;
    	}
    	
    	var pad = "00000000"
    	var str = value.toString();
    	
    	return pad.substring(0, pad.length - str.length) + str;
    	
    };
});
