<template>
  <div class="login-box">
    <h1>Login</h1><br/>
    <label for="id">ID:</label>
    <label><input type="text" id="id"></label><br/>
    <label for="pw">PW:</label>
    <label><input type="password" id="pw"></label><br/>

    <CommonButton text="Login" route="/"/><br/>
    <CommonButton text="SignUp" route="/signup"/>
  </div>
</template>

<script src="@/assets/js/UserLogin.js"/>
<style src="@/assets/css/UserLogin.css" scoped/>