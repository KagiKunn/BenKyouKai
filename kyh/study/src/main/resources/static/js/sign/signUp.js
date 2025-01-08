document.addEventListener("DOMContentLoaded", function(){
    //element name
    const signup_btn = document.getElementById("signup-btn-1");
    const signup_cancel_btn = document.getElementById("signup-btn-2");
    const check_id_btn = document.getElementById("check-id-btn");
    const input_img_btn = document.getElementById("input-img");

    //global variable
    let check_id_result = false;

    //go back page
    const goBack = () => {
        console.log("check in");
        location.href="/";
    }

    //preview img
    const previewImg = (e) => {
        const file = e.target.files[0];
        const reader = new FileReader();
        reader.onload = (e) => {
            let img = document.getElementById("img-user");
            img.src = e.target.result;
        };
        reader.readAsDataURL(file);
    }

    //signup
    const doSignUp = () => {
        //input name
        let id = document.getElementById("input-id").value;
        let pw = document.getElementById("input-pw").value;
        let nickname = document.getElementById("input-nick").value;
        let age = document.getElementById("input-age").value;
        age = Number(age);
        let img = document.getElementById("input-img").files[0];

        //form data
        const formData = new FormData();
        formData.append("id", id);
        formData.append("pw", pw);
        formData.append("nick", nickname);
        formData.append("age", age);
        formData.append("img", img);

        //signup
        axios.post("/sign/doSignUp", formData)
            .then(res => {
                if(res.status === 200) {
                    console.log("성공");
                    alert("회원가입 축하합니다!");
                    location.href = "/";
                }
            })
            .catch(err => {
                console.error(err);
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
    input_img_btn.addEventListener("change", (event) => {
        previewImg(event);
    })
});