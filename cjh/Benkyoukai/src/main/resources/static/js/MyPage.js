function deleteAccount(id) {
    let check = confirm("Are you sure you want to delete your account?");
    const id1 = document.getElementById("id1").value;
    if (check) {
        try {
            console.log(check)
            console.log(id1)
            const response = fetch('/delete?id=' + id1, {
                method: 'GET',
                headers: { 'Content-Type': 'application/json' }
            });

            // 비동기적으로 결과를 처리
            response.then(result => result.text())  // result.text()는 비동기적으로 실행
                .then(resultText => {
                    console.log(resultText);  // 서버로부터 받은 응답 출력

                    // 응답 결과에 따라 분기
                    if (resultText === "deleted") {
                        alert("Your account has been successfully deleted.");
                    } else {
                        alert("Error occurred; try again later.");
                    }

                    window.location.href = '/';  // 완료 후 리디렉션
                })
                .catch(error => {
                    alert("Error occurred: " + error.message);
                });
        } catch (error) {
            alert("Error occurred: " + error.message);
        }
    }
}
