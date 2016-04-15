'use strict'
class CartCtrl {
	constructor($state, CartService) {
		this.$state = $state;
		this.CartService = CartService;

		this.id = $state.params.bookId;

		this.getCart();
	}

	getCart() {
		let self = this;
		self.CartService.get(function(result) {
			// console.log(result);
			self.cart = result;
		});
	}

	deleteBook(index) {
		let self = this;
		self.CartService.deleteBook({bookId: self.cart[index].bookId}, {}, function() {
			alert("已移除出购物车");
			self.getCart();
		});
	}

	toOrder() {
		this.CartService.toOrder(function(result) {
			alert("已成功生成订单")
		});
	}
}


angular.module('cartModule', [])
.controller('cartCtrl', ['$state', 'CartService', CartCtrl])