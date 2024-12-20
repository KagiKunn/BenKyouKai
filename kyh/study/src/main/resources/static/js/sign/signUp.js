import("static/js/common/common");

document.addEventListener("DOMContentLoaded", function(){
    const signup_btn = document.getElementById("signup-btn-1");
    const signup_cancel_btn = document.getElementById("signup-btn-2");

    const goBack = () => {
        console.log("check in");
        location.href="/";
    }

    const doSignUp = () => {
        let id = document.getElementById("input-id").value;
        let pw = document.getElementById("input-pw").value;
        let nickname = document.getElementById("input-nick").value;
        let age = document.getElementById("input-age").value;
        age = Number(age);
        // if(regId.test(id) && regPw(pw) && regNick(nickname) && regAge(age)) {
        //
        // }

        axios.post("/sign/doSignUp", {
            id,
            pw,
            nick: nickname,
            age
        })
            .then(res => {
                if(res.status === 200) {
                    console.log("성공");
                    alert("회원가입 축하합니다!");
                    location.href = "/sign/result";
                }
            }).catch(err => {
            console.error(err);
            location.href = "/sign/goSignUp";
        })
    }

    signup_btn.addEventListener("click", () => {
        doSignUp();
    })

    signup_cancel_btn.addEventListener("click", () => {
        goBack();
    })
});