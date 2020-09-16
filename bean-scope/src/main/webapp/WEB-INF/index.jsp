<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>首页</title>
</head>
<body>
Hello World!<br/>
\${user}: ${user}<br/>
\${user.name}: ${user.name}<br/>
<br/>
<br/>
<br/>
\${applicationScope['scopedTarget.user']}: ${applicationScope['scopedTarget.user']}
\${applicationScope['scopedTarget.user'].name}: ${applicationScope['scopedTarget.user'].name}
</body>
</html>