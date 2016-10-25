produtoModule.factory("service", function($http) {
	
	var _getProdutos = function() {
		
		return $http.get('rest/produtos');
		//http://localhost:8080/carrinhodecompras/rest/produtos
		
	};
	
	var _getCarrinho = function() {
		
		return $http.get('rest/carrinho');
		//http://localhost:8080/carrinhodecompras/rest/carrinho
		
	};
	
	var _addProduto = function(id, qtd) {
		
		var param = "/"+id+"/"+qtd;
		
		return $http.put('rest/carrinho' + param);
		//http://localhost:8080/carrinhodecompras/rest/carrinho/id/qtd
		
	}
	
	var _removeProduto = function(id, qtd) {
		
		var param = "/"+id+"/"+qtd;
		
		return $http.delete('rest/carrinho' + param);
		//http://localhost:8080/carrinhodecompras/rest/carrinho/id/qtd
		
	}
	
	var _postCarrinho = function(carrinho){
		
		return $http.post('rest/pedido', angular.toJson(carrinho));
		//http://localhost:8080/carrinhodecompras/rest/carrinho/
		
	}
	
	var _validarFormularioCompra = function(formulario){
		
		var mensagem = [];
		
		if($.trim(formulario.nroCartao) == ''){
			mensagem.push("Informar o número do cartão.");
		}
		if($.trim(formulario.nmCartao) == ''){
			mensagem.push("Informar o nome impresso no cartão.")
		}
		if($.trim(formulario.cpf) == ''){
			mensagem.push("Informar o número do cpf do titular do cartão.")
		}
		if($.trim(formulario.cdSeguranca) == ''){
			mensagem.push("Informar o código de segurança do cartão.")
		}
		
		return mensagem;
		
	}
	
	return {
		
		getListaProdutos : _getProdutos,
		getCarrinho : _getCarrinho,
		addProduto : _addProduto,
		removeProduto : _removeProduto,
		finalizarCompra : _postCarrinho,
		validarFormularioCompra : _validarFormularioCompra
		
	};
	
});