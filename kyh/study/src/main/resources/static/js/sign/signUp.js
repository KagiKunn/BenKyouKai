document.addEventListener("DOMContentLoaded", function(){
    //element name
    const signup_btn = document.getElementById("signup-btn-1");
    const signup_cancel_btn = document.getElementById("signup-btn-2");
    const check_id_btn = document.getElementById("check-id-btn");

    //global variable
    let check_id_result = false;

    //go back page
    const goBack = () => {
        console.log("check in");
        location.href="/";
    }

    //signup
    const doSignUp = () => {
        //input name
        let id = document.getElementById("input-id").value;
        let pw = document.getElementById("input-pw").value;
        let nickname = document.getElementById("input-nick").value;
        let age = document.getElementById("input-age").value;
        age = Number(age);

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
                    location.href = "/";
                }
            })
            .catch(err => {
                console.error(err);
                location.href = "/sign/goSignUp";
            })
    }

    //check id
    const doCheckId = () => {
        //input name
        let id = document.getElementById("input-id").value;

        console.log("check id => "+id);

        axios.get("/sign/doCheckId", {
            params : {
                id
            }
        }).then((res) => {
            if(res.data === 0) {
                alert("가입했던 아이디 또 못만들어유");
            } else {
                alert("가입 가능한 아이디에유");
                check_id_result = true;
            }
        }).catch((err) => {
            console.error(err);
        })
    }

    //click event
    signup_btn.addEventListener("click", () => {
        if(check_id_result) {
            doSignUp();
        } else {
            alert("ID 중복확인 하셔야쥬.")
        }
    })
    signup_cancel_btn.addEventListener("click", () => {
        goBack();
    })
    check_id_btn.addEventListener("click", () => {
        doCheckId();
    })
});