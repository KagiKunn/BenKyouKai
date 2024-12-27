window.onload = function() {
    // 세션에서 로그인 상태를 확인
    // HTML 요소에서 loggedIn 값을 가져오기
    const loggedInElement = document.getElementById('app');
    const loggedIn = loggedInElement ? loggedInElement.getAttribute('data-loggedIn') === 'true' : true;
    // Spring Model에서 전달된 값
    console.log(loggedIn);


    if (loggedIn) {
        document.getElementById('before_login_btn').style.display = 'none';
        document.getElementById('after_login_btn').style.display = 'block';
    } else {
        document.getElementById('before_login_btn').style.display = 'block';
        document.getElementById('after_login_btn').style.display = 'none';
    }
};