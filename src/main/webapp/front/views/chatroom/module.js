/**
 * Created by fish on 4/17/16.
 */
'use strict'
class ChatroomCtrl {
    constructor($state, $websocket, UserService) {
        this.$state = $state;
        this.$websocket = $websocket;
        this.UserService = UserService;
        this.contents = [];

        this.connect()


    }

    connect() {
        let self = this;
        self.UserService.get(function (result) {
            self.name = result.name;

            self.connection = self.$websocket('ws://localhost:8081/Bookstore/websocketbot');
            self.connection.send({
                type: 'join',
                name: self.name
            });

            self.connection.onMessage(function (result) {
                let message = JSON.parse(result.data);
                let content = {};
                if (message.type == 'chat') {
                    content.type = 'chat';
                    content.prompt = message.name;
                    content.content = message.message;
                    self.contents.push(content);
                }
                else if (message.type == 'info') {
                    content.type = 'info';
                    content.prompt = '提示';
                    content.content = message.info;
                    self.contents.push(content);
                }
                else if(message.type = 'users') {
                    self.users = message.userlist;
                }
            });
        }, function (err) {
            if (err.status == 403) {
                alert("请先登录");
                self.$state.go('login');
            }
        })
    }

    send() {
        let self = this;
        self.connection.send({
            type: 'chat',
            name: self.name,
            message: self.message
        }, function () {
        });
        self.message = '';
    }

}


angular.module('chatroomModule', [])
    .controller('chatroomCtrl', ['$state', '$websocket', 'UserService', ChatroomCtrl])