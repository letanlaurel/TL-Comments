//模拟评论数据
const comment = {
    status: "成功",
    code: 200,
    data: [
        {
            id: 'comment0001', //主键id
            date: '2018-07-05 08:30',  //评论时间
            ownerId: 'talents100020', //文章的id
            fromId: 'errhefe232213',  //评论者id
            fromName: '犀利的评论家',   //评论者昵称
            fromAvatar: 'http://ww4.sinaimg.cn/bmiddle/006DLFVFgy1ft0j2pddjuj30v90uvagf.jpg', //评论者头像
            likeNum: 5, //点赞人数
            content: '非常靠谱的程序员',  //评论内容
            reply: [  //回复，或子评论
                {
                    id: '34523244545',  //主键id
                    commentId: 'comment0001',  //父评论id，即父亲的id
                    fromId: 'observer223432',  //评论者id
                    fromName: '夕阳红',  //评论者昵称
                    fromAvatar: 'https://wx4.sinaimg.cn/mw690/69e273f8gy1ft1541dmb7j215o0qv7wh.jpg', //评论者头像
                    toId: 'errhefe232213',  //被评论者id
                    toName: '犀利的评论家',  //被评论者昵称
                    toAvatar: 'http://ww4.sinaimg.cn/bmiddle/006DLFVFgy1ft0j2pddjuj30v90uvagf.jpg',  //被评论者头像
                    content: '赞同，很靠谱，水平很高',  //评论内容
                    date: '2018-07-05 08:35',   //评论时间
                    likeNum: 3, //点赞人数
                },
                {
                    id: '34523244545',
                    commentId: 'comment0001',
                    fromId: 'observer567422',
                    fromName: '清晨一缕阳光',
                    fromAvatar: 'http://imgsrc.baidu.com/imgad/pic/item/c2fdfc039245d688fcba1b80aec27d1ed21b245d.jpg',
                    toId: 'observer223432',
                    toName: '夕阳红',
                    toAvatar: 'https://wx4.sinaimg.cn/mw690/69e273f8gy1ft1541dmb7j215o0qv7wh.jpg',
                    content: '大神一个！',
                    date: '2018-07-05 08:50',
                    likeNum: 2, //点赞人数
                }
            ]
        },
        {
            id: 'comment0002',
            date: '2018-07-05 08:30',
            ownerId: 'talents100020',
            fromId: 'errhefe232213',
            fromName: '毒蛇郭德纲',
            fromAvatar: 'http://ww1.sinaimg.cn/bmiddle/006DLFVFgy1ft0j2q2p8pj30v90uzmzz.jpg',
            likeNum: 0,
            content: '从没见过这么优秀的人',
            reply: []
        }
    ]
};
const user = { //用于信息
    id:"12345aaa",
    name:"小花",
    avatar:"icon.jpg"
};
let methodType = true; //为true代表父评论反之则代表子评论
const textContent = { //文章信息
    id:"talents100020",
    content:"文章内容",
    images:['icon.jpg','a.jpg'],
    vedios:"aa.mp4",
    writer_id:2
};

$(function () {
        $.ajax({
            url:'http://127.0.0.1:8080/getListByOwnerId',
            data:{ownerId:textContent.id,userId:user.id},
            type:"post",
            dataType:"json",
            success:function (res) {
                console.log(res);
                let data = res.data;
                let likes = res.addData;
                $.each(data,function (i,e) {
                    e.createTime = new Date(e.createTime).toLocaleDateString()
                });
                $("#tlgg").html(template("ta",{data:data}));
                console.log(likes)
                for (let i = 0; i < likes.length; i++) {
                    islikemap.set(likes[i].objId,likes[i].likeStatus);
                }
            },
            error:function (res) {
                console.log(res.status);
            }
        })


    $("#tj").click(function () {
       let content =  $("#content").val();
        let option = JSON.parse(sessionStorage.getItem("option"));
        if(methodType) {
            $.ajax({
                url: 'http://127.0.0.1:8080/addRootComments',
                type: "post",
                data: {
                    type: 1,
                    ownerId:textContent.id, //文章的id
                    fromId: user.id,  //评论者id
                    fromName: user.name,   //评论者昵称
                    likeNum:0,
                    content: content,  //评论内容
                    fromAvatar: user.avatar
                },
                dataType: "json",
                success: function (res) {
                    console.log(res);
                    let data = res.data;
                    $("#tlgg").html(template("ta", {data: data}));
                }
            })
        }else{
            console.log(option);
            $.ajax({
                url: 'http://127.0.0.1:8080/addSonComments',
                type: "post",
                data: {
                    id:option.id,
                    fromId: user.id,  //评论者id
                    fromName: user.name,   //评论者昵称
                    content: $("#content").val(),  //评论内容
                    fromAvatar: user.avatar,
                    likeNum:0,
                    toId:option.toId,
                    toName:option.toName
                },
                dataType: "json",
                success: function (res) {
                    console.log(res);
                    // let data = res.data;
                    // $("#tlgg").html(template("ta", {data: data}));
                    methodType = true;
                }
            })
        }
        $("#content").html("");
    })

});

function receive(id,ownerId,toId,toName) {
    $("#content").focus();
    $("#content").prop("placeholder","回复:"+toName);
        methodType = false;
        let option = {
            id:id,
            ownerId:ownerId,
            toId:toId,
            toName:toName
        };
        sessionStorage.setItem("option",JSON.stringify(option));
        $("#qx").show();
    console.log(ownerId)
    console.log(methodType)
    console.log(id)
}
function quxiao(obj) {
    methodType = true;
    $("#content").prop("placeholder","留下你对文章的看法!");
    $(obj).hide();
}
function textchange(obj) {
    let lenght = $(obj).val().length;
    $("#textCount").html(200-lenght);
    if(lenght == 0)
        $("#tj").prop("disabled","true");
    else
        $("#tj").removeProp("disabled");
}
let flag = true;
function showSonComments(obj,i) {
    if(flag) {
        $("#sonComments"+i).show();
        $(obj).html("收起回复");
        flag = false;
    }else{
        $("#sonComments"+i).hide();
        $(obj).html("查看回复");
        flag = true;
    }
}
// let starMap = new Map();
// function dianzan(commentId,id,likeNum,obj) {
//     likeNum = parseInt(likeNum);
//     starMap.forEach(console.log);
//     // if(likeNum < parseInt(sessionStorage.getItem(id + "*" + user.id+"*"+commentId))) {
//     //     //设置用户的id到缓存中
//     //     sessionStorage.setItem(id + "*" + user.id + "*" + commentId, likeNum);
//     // }else{
//     //     sessionStorage.setItem(id + "*" + user.id + "*" + commentId, likeNum + 1);
//     // }
//     $(obj).html(sessionStorage.getItem(id + "*" + user.id+"*"+commentId));
//     // starMap.set(id + "*" + user.id+"*"+commentId,sessionStorage.getItem(id + "*" + user.id+"*"+commentId));
//     var starFlag = starMap.get(id + "*" + user.id+"*"+commentId);
//      if(starFlag !== undefined && starFlag !== false){
//          // if()
//          starMap.set(id + "*" + user.id+"*"+commentId,true);
//      }
//     starMap.set(id + "*" + user.id+"*"+commentId,likeNum);
// }

// function dianzan(commentId,id,likeNum,obj) {
//     var starflag = sessionStorage.getItem(id + "*" + user.id + "*" + commentId);
//     if(starflag === "true"){
//         if(sessionStorage.getItem(id + "**" + user.id + "**" + commentId)==="1"){
//             $(obj).html(parseInt(likeNum)-1);
//         }else {
//             $(obj).html(parseInt(likeNum));
//         }
//         sessionStorage.setItem(id + "*" + user.id + "*" + commentId, "false");
//         $(obj).css("color","black");
//     }else{
//         $(obj).html(parseInt(likeNum)+1);
//         sessionStorage.setItem(id + "*" + user.id + "*" + commentId, "true");
//         sessionStorage.setItem(id + "**" + user.id + "**" + commentId, "1");
//         $(obj).css("color","red");
//     }
// }

// function dianzan(obj) {
//     if(!obj.className){
//         obj.className = "liked";
//     }else{
//         obj.className = "";
//     }
//     $.ajax({
//         url:'http://127.0.0.1:8080/isLike',
//         type:"post",
//         data:{
//             id:$(obj).attr("commId"),
//             commentId:$(obj).attr("commentId"),
//             userId:user.id
//         },
//         dataType:"json",
//         success:function (res) {
//             sessionStorage.setItem("isliked"+$(obj).attr("commId"),res.msg);
//             var likeNum = parseInt($(obj).attr("likeNum"));
//             if(res.msg === "liked"){
//                 $(obj).html(likeNum+1);
//             }else{
//                 $(obj).html(likeNum);
//             }
//         }
//     })
// }


// function dianzan(obj) {
//     let k = $(obj).attr("data-commId")+"*"+$(obj).attr("data-commentId");
//     if(sessionStorage.getItem(k) == null){
//         sessionStorage.setItem(k,0);
//     }
//     let v = parseInt(sessionStorage.getItem(k));
//     sessionStorage.setItem(k,(v+1));
//     let uv = sessionStorage.getItem(user.id);
//     if(uv != null) {
//         sessionStorage.setItem(user.id, uv+"\n"+k + "*" + v);
//     }else{
//         sessionStorage.setItem(user.id,k + "*" + v)
//     }
//     let v2 = parseInt(sessionStorage.getItem(k));
//     var likeNum = parseInt($(obj).attr("data-likeNum"));
//     console.log(v2);
//     if(v2%2 ===  1){
//         obj.className = "liked";
//         $(obj).html(likeNum+1);
//     }else{
//         obj.className = "";
//         $(obj).html(likeNum);
//     }
// }
let islikemap = new Map();
function dianzan(obj) {
    let likeNum = parseInt($(obj).attr("data-likeNum"));
    let likesta = islikemap.get($(obj).attr("data-commentId"));
    $.ajax({
        url:'http://127.0.0.1:8080/isLike',
        type:"post",
        data:{
            objId:$(obj).attr("data-commentId"),
            userId:user.id,
            likeStatus:likesta,
            commType:$(obj).attr("data-commType")
        },
        dataType:"json",
        success:function (res) {
            console.log(likeNum);

                if(res.data.likeStatus != "-1") {
                    obj.className = 'liked';
                    $(obj).html(likeNum);
                }
                else {
                    obj.className = "";
                    $(obj).html(likeNum  + parseInt(res.data.likeStatus));
                }
        }
    })
}