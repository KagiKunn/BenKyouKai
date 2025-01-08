document.addEventListener("DOMContentLoaded", function() {
    const input_img_btn = document.getElementById("input-img");
    const update_btn_1 = document.getElementById("update-btn-1");
    const update_btn_2 = document.getElementById("update-btn-2");

    //delete
    window.deleteUser = (id) => {
        const check_delete = confirm("삭제하시겠어요?");
        if(check_delete) {
            axios.post("/mypage/delete", {
                id
            })
                .then(res => {
                    if(res.data === 1) {
                        location.href = "/";
                    }
                })
                .catch(err => {
                    console.error(err);
                })
        }
    };

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

    //update
    const updateUser = () => {
        let newId = document.getElementById("input-id").value;
        let newPw = document.getElementById("input-pw").value;
        let newNick = document.getElementById("input-nick").value;
        let img = document.getElementById("input-img").files[0];

        const formData = new FormData();
        formData.append("id", newId);
        formData.append("pw", newPw);
        formData.append("nick", newNick);
        formData.append("img", img);

        axios.post("/mypage/update", formData)
            .then(res => {
                if(res.data === 1) {
                    alert("수정 완료되었습니다.");
                    location.href="/";
                }
            })
            .catch(err => {
                alert("수정실패");
                console.error(err);
            })
    }

    //go update page
    window.goUpdatePage = () => {
        location.href = "/mypage/updatepage";
    }

    //go back page
    const goBackPage = () => {
        location.href="/";
    }

    if(update_btn_1) {
        update_btn_1.addEventListener("click", () => {
            updateUser();
        })
    }
    if(update_btn_2) {
        update_btn_2.addEventListener("click", () => {
            goBackPage();
        })
    }
    if(input_img_btn) {
        input_img_btn.addEventListener("change", (event) => {
            previewImg(event);
        })
    }
})
