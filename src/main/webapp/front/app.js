var myApp = angular.module('myApp',[
  'ui.bootstrap',
	'ui.router',
  'ngMessages',
  'ngAnimate',
    'ngWebSocket',

  'resources',
  'navModule',

	'loginModule',
  'bookModule',
  'cartModule',
  'orderModule',
  'manageModule',
    'chatroomModule'

  ]);

 
myApp.config(function($stateProvider, $urlRouterProvider) {
  
  $urlRouterProvider.otherwise('404')
  .when('', 'login');
  
  $stateProvider
    .state('login', {
      url: '/login',
      templateUrl: 'views/login/login.html',
      controller: 'loginCtrl as ctrl'
    })
    .state('signup', {
      url: '/signup',
      templateUrl: 'views/login/signup.html',
      controller: 'signupCtrl as ctrl'
    })
    .state('nav', {
      url: "/nav",
      templateUrl: "views/nav.html",
      controller: 'navCtrl as ctrl',
      abstract: false
    })

    /*
    ** book list
    */
    .state('nav.book', {
      url: '/book/list',
      templateUrl: 'views/book/list.html',
      controller: 'bookListCtrl as ctrl',
      data: {
        nav: 'book'
      }
    })

    /*
    ** cart
    */
    .state('nav.cart', {
      url: '/cart',
      templateUrl: 'views/cart/cart.html',
      controller: 'cartCtrl as ctrl',
      data: {
        nav: 'cart'
      }
    })

    /*
    ** order list
    */
    .state('nav.order', {
      url: '/order/list',
      templateUrl: 'views/order/list.html',
      controller: 'orderCtrl as ctrl',
      data: {
        nav: 'order'
      }
    })

    /*
    ** chat room
    */
      .state('nav.chatroom', {
        url: '/chatroom',
        templateUrl: 'views/chatroom/chatroom.html',
        controller: 'chatroomCtrl as ctrl',
        data: {
          nav: 'chatroom'
        }
      })
    /*
    ** magage
    */
    .state('nav.manage', {
      url: '/manage',
      templateUrl: 'views/manage/manage.html',
      controller: 'manageCtrl as ctrl',
      data: {
        nav: 'manage'
      }
    })
    .state('nav.manage.book', {
      url: '/book',
      templateUrl: 'views/manage/book.html',
      controller: 'bookManageCtrl as ctrl',
      data: {
        page: 'book'
      }
    })
    .state('nav.manage.user', {
      url: '/user',
      templateUrl: 'views/manage/user.html',
      controller: 'userManageCtrl as ctrl',
      data: {
        page: 'user'
      }
    })


    /*
    **  not found
    */
    .state('404', {
      url: '/404',
      templateUrl: '404.html'
    });
});