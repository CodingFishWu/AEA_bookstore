"use strict";

class navCtrl {
	constructor($state) {
		this.$state = $state;
	}
}

angular.module('navModule', ['ui.router'])
.controller('navCtrl', ['$state', navCtrl]);
