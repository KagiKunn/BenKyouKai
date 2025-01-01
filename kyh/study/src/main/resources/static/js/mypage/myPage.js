document.addEventListener("DOMContentLoaded", function() {
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

    //update
    window.updateUser = () => {
        let newId = document.getElementById("input-id").value;
        let newPw = document.getElementById("input-pw").value;
        let newNick = document.getElementById("input-nick").value;

        axios.post("/mypage/update",{
            id:newId,
            pw:newPw,
            nick:newNick
        })
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
    window.goBackPage = () => {
        location.href="/";
    }
})
