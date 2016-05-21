<!DOCTYPE html>
<html lang="en">
<head>

    <jsp:include page="headers.jsp"/>

<body>

<jsp:include page="menu.jsp"/>

<section id="form"><!--form-->
    <div class="container">
        <div class="row">
            <div class="col-sm-4 col-sm-offset-1">
                <div class="login-form"><!--login form-->
                    <h2>Login to your account</h2>

                    <form action="j_spring_security_check" method='POST'>
                        <td>User:</td>
                        <td><input type='text' name='username' value=''></td>
                        </tr>
                        <tr>
                            <td>Password:</td>
                            <td><input type='password' name='password'/></td>
                            <input type="hidden"
                                   name="${_csrf.parameterName}"
                                   value="${_csrf.token}"/>

                            <button type="submit" class="btn btn-default">Login</button>
                    </form>
                </div>
                <!--/login form-->
            </div>
            <div class="col-sm-1">
                <h2 class="or">OR</h2>
            </div>
            <div class="col-sm-4">
                <div class="signup-form"><!--sign up form-->
                    <h2>New User Registration!</h2>

                    <form action="/index" method="POST">
                        <input name= 'username' type="text" placeholder="Name"/>
                        <input name = 'email' type="email" placeholder="Email Address"/>
                        <input name = 'password' type="password" placeholder="Password"/>
                        <button type="submit" class="btn btn-default">Signup</button>
                    </form>
                </div>
                <!--/sign up form-->
            </div>
        </div>
    </div>
</section>
<!--/form-->

<jsp:include page="footer.jsp"/>
<jsp:include page="js-scripts.jsp"/>

</body>
</html>
