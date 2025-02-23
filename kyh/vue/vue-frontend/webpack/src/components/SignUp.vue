<template>
  <div>
    <h1>회원가입 페이지입니다만?</h1>
    <label for="id">ID:</label>
    <label><input type="text" id="id"></label><br/>
    <label for="pw">PW:</label>
    <label><input type="password" id="pw"></label><br/>
    <label for="nick">Nick:</label>
    <label><input type="text" id="nick"></label><br/>
    <label for="age">age:</label>
    <label><input type="number" id="age" maxlength="2"></label><br/>
    <CommonButton text="SignUp" route="/"/><br/>
    <CommonButton text="Cancel" route="/"/>
  </div>
</template>

<script src="@/assets/js/SignUp.js"/>
