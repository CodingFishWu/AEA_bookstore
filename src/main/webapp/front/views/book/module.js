'use strict'

class BookListCtrl {
	constructor($state, BookService, CartService) {
		this.$state = $state;
		this.BookService = BookService;
		this.CartService = CartService;

		this.title = "最新图书";
		this.categories = ["art", "science", "math"];
		this.param = {};

		let self = this;
		BookService.query(function(result) {
			self.books = result;
			self.backups = result;
		});
	}

	addToCart(index) {
		let self = this;
		self.CartService.addBook({bookId: self.books[index].id}, {}, function() {
			alert("添加成功");
		}, function(result) {
			if (result.status == 403) {
				alert("请先登录");
				self.$state.go('login');
			}
		});
	}

	query() {
		// let self = this;
		// self.BookService.query({
		// 	name: self.param.name,
		// 	author: self.param.author
		// }, function(result) {
		// 	self.books = result;
		// 	self.backups = result;
		// }, function(result, err) {
		// 	console.log(err);
		// });
	}

	filt(category) {
		let self = this;
		self.books = [];
		for (let book of self.backups) {
			if (book.catagory == category) {
				self.books.push(book);
			}
		}
	}

	viewDetail(index) {
		// let self = this;
		// self.$state.go('nav.bookDetail', {bookId: self.books[index].id});
	}
}

angular.module('bookModule', [])
.controller('bookListCtrl', ['$state', 'BookService', 'CartService', BookListCtrl])
