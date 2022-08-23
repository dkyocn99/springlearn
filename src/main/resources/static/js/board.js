let index = {
    init: function () {
        //let _this = this; function을 사용할 때 선언
        //첫번쨰 파라메터에는 어떤 이벤트 두번째 파라메터에는 무엇을 할꺼야?
        $("#btn-save").on("click", () => { //function(){}을 사용하지 않고 ()=>{}을 사용하는 이유는 this를 바인딩하기 위해
            this.save();
        });
    },
        save: function () {

            let data = {
                title: $("#title").val(),
                content: $("#content").val()
            };

            $.ajax({
                //회원가입 수행 요청(100초 가정)
                type: "POST",
                url: "/api/board",
                data: JSON.stringify(data),
                contentType: "application/json;",
                dataType: "json"
            }).done(function (resp) {
                alert("글쓰기가 완료되었습니다.");
                console.log(resp);
                // alert(resp);
                location.href = "/"
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        }
    }



index.init();