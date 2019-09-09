function post() {
    var postId = $("#post_id").val();
    var content = $("#comment_content").val();
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": postId,
            "content": content,
            "type": 1
        }),
        success: function (response) {
            if (response.code ==200){
                $("#comment_section").hide();
            }else {
                if (response.code ==2003){
                    var isAccepted = confirm(response.message);
                    if (isAccepted){
                        window.open("https://github.com/login/oauth/authorize?client_id=b5e2cfb5f4928aaa5290&redirect_uri=http://localhost:8080/callback&scope=user&state=8848")
                        window.localStorage.setItem("closable",true);
                    }
                }else {
                    alert(response.message);
                }

            }
            console.log(response);
        },
        dataType:"json"
    });
}