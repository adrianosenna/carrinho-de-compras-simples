produtoModule.controller('controller', function($scope, service) {
	
	$("#qtd-remove").prop("disabled", false);
	
	$scope.qtdAdc;
	
	$scope.onlyNumbers = /^[0-9]{1,7}$/;
	
	//obtem produtos e valores da compra no carrinho
	var obterCarrinho = service.getCarrinho().then(function(response){
		$scope.carrinho = response.data;
	});
	
	//lista todos os produtos
	var listarProdutos = service.getListaProdutos().then(function(response){
		$scope.listaDeProdutos = response.data;
	});
	
	//adiciona produto no carrinho
	$scope.adicionarProduto = function(id, qtd){
		
		if(qtd <= 0){
			
			return;
			
		}
		
		service.addProduto(id, qtd).then(function(response){
			$scope.carrinho = response.data;
		});
	};
	
	//remove produto do carrinho
	$scope.removerProduto = function(id, qtd, removerTodo) {
		
		if(!removerTodo && qtd <= 0){
			
			return;
			
		}
		
		service.removeProduto(id, qtd).then(function(response){
			$scope.carrinho = response.data;
			$('#myModal').modal('hide');
		});
	};
	
	//enviar carrinho de compras
	$scope.finalizarCompra = function() {
		
		var validacao = service.validarFormularioCompra($scope.formularioCompra);
		if(validacao.length > 0){
			$scope.formularioCompra.critica = validacao;
			return;
		}
		
		service.finalizarCompra($scope.carrinho).then(function(response){
			$scope.resumoCompra = response.data;
			$('#modalfinalizarPedido').modal('hide');
			$('#statusCompraModal').modal('show');
		});
		
	};
	
	var controlarQtdRemovida = $scope.controlarQtdRemovida = function() {
		
		$("#qtd-remove").prop("disabled", false);
		
		if($scope.auxProduto.removerTodo){
			
			$scope.auxProduto.qtdRemover = $scope.auxProduto.qtdCarrinho;
			
			$("#qtd-remove").prop("disabled", true);
			
		}
		
	}
	
	var abrirModal = $scope.abrirModal = function(id, nome, qtd) {
		
		$scope.auxProduto = jQuery.parseJSON('{ "id":"'+ id + '", "nome": "'+nome+'", "qtdCarrinho": '+qtd+' , "removerTodo" : "false", "qtdRemover":'+qtd+'}');
		
		controlarQtdRemovida();
		
		$('#myModal').modal('show');
		
	};
	
	var abrirModalFinalizarCompra = $scope.abrirModalFinalizarCompra = function(){
		
		$scope.formularioCompra = jQuery.parseJSON('{"nroCartao":"","nmCartao":"","cpf":"","critica":"", "cdSeguranca": ""}');
		
		$('#modalfinalizarPedido').modal('show');
		
	}
	
	//calcula valor total do item no carrinho
	$scope.getSubtotal = function(produto) {
		
		return produto.valorUnitario * produto.quantidade;
		
	}
	
	
});