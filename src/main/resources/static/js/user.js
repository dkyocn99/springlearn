let index = {
    init: function () {
        //let _this = this; function을 사용할 때 선언
        //첫번쨰 파라메터에는 어떤 이벤트 두번째 파라메터에는 무엇을 할꺼야?
        $("#btn-save").on("click", () => { //function(){}을 사용하지 않고 ()=>{}을 사용하는 이유는 this를 바인딩하기 위해
            this.save();
        });
        $("#btn-update").on("click", () => {
            this.update();
        });
    },

    save: function () {
        //alert("user의 save함수 호출됨");
        let data = {
            username:$("#username").val(),
            password:$("#password").val(),
            email:$("#email").val()
        };

        //console.log(data);

        //ajax호출 시 default가 비동기 호출
        //ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
        //ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 java오브젝로 변환해줌
        $.ajax({
            //회원가입 수행 요청(100초 가정)
            type:"POST",
            url:"/auth/joinProc",
            data: JSON.stringify(data), //http body데이터
            contentType: "application/json;", //body데이터가 어떤 타입인지(MIME)
            dataType: "json" //요청을 서버로 응답이 왔을 떄 기본적으로 모든 것이 문자열(생긴게 json이라면) => javascript오브젝트로 변경
        }).done(function(resp) {
            alert("회원가입이 완료되었습니다.");
            console.log(resp);
            // alert(resp);
            location.href="/"
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    update: function () {
        let data = {
            id:$("#id").val(),
            username: $("#username").val(),
            password:$("#password").val(),
            email:$("#email").val()
        };
        $.ajax({
            //회원가입 수행 요청(100초 가정)
            type:"PUT",
            url:"/user",
            data: JSON.stringify(data), //http body데이터
            contentType: "application/json;", //body데이터가 어떤 타입인지(MIME)
            dataType: "json" //요청을 서버로 응답이 왔을 떄 기본적으로 모든 것이 문자열(생긴게 json이라면) => javascript오브젝트로 변경
        }).done(function(resp) {
            alert("회원수정이 완료되었습니다.");
            console.log(resp);
            location.href="/"
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
}

index.init();