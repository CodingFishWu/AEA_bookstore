'use strict'
class OrderCtrl {
	constructor($state, OrderService) {
		this.$state = $state;
		this.OrderService = OrderService;

		this.showItem = -1;

		let self = this;
		OrderService.query(function(result) {
			self.orders = result;
			console.log(result);
		}, function(err) {
			if (err.status == 401) {
				alert('请先登录');
				self.$state.go('login')
			}
		});
	}

	show(index) {
		if (this.showItem == index) {
			this.showItem = -1;
		}
		else {
			this.showItem = index;
		}
	}
}

angular.module('orderModule', [])
.controller('orderCtrl', ['$state', 'OrderService', OrderCtrl]);