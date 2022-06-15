/*
*author:yhdhz
*data:2022/5/26 12:44 58
*file name:login_registered.js
*/

function login(form)
{

    $.ajax({
        url:"/api/user/login/post",
        type:"post",
        content_type:"application/json;charset=utf-8",
        dataType:"json",
    })
}

function checkMailbox()
{
    var email = $("#email");

}