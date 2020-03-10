<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>

<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 内容管理</title>
    <jsp:include page="../includes/header.jsp"/>
    <link rel="stylesheet" href="../../static/assets/plugins/treeTable/themes/vsStyle/treeTable.min.css"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp"/>
    <jsp:include page="../includes/menu.jsp"/>
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                内容管理
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">

                    <c:if test="${baseResult!=null}">
                        <div class="alert alert-${baseResult.status==200?"success":"danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <h4><i class="icon fa fa-ban"></i> Alert!</h4>
                                ${baseResult.message}
                        </div>
                    </c:if>



                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">分类列表</h3>
                        </div>
                        <div class="box-body">
                            <a href="/content/category/form" type="button" class="btn btn-default btn-sm"><i class="fa fa-plus"></i>新增</a>&nbsp;&nbsp;&nbsp;
                            <a href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-download"></i>导入</a>&nbsp;&nbsp;&nbsp;
                            <a href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-upload"></i>导出</a>&nbsp;&nbsp;&nbsp;
                        </div>

                        <!-- /.box-header -->
                        <div class="box-body table-responsive ">
                            <table id="treeTable" class="table table-hover">
                                <thead>
                                <tr>
                                    <td>ID</td>
                                    <td>名称</td>
                                    <td>排序</td>
                                    <td>操作</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${tbContentCategories}" var="tbContentCatrgory">
                                    <tr id="${tbContentCatrgory.id}" pId="${tbContentCatrgory.parentId}">
                                        <td>${tbContentCatrgory.id}</td>
                                        <td>${tbContentCatrgory.name}</td>
                                        <td>${tbContentCatrgory.sortOrder}</td>
                                        <td>
                                            <a href="#" type="button" class="btn btn-primary btn-sm"><i class="fa fa-edit"></i>编辑</a>&nbsp;&nbsp;&nbsp;
                                            <button type="button" class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i>删除</button>&nbsp;&nbsp;&nbsp;
                                            <a href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-plus"></i>新增下级菜单</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </section>
    </div>

    <jsp:include page="../includes/copyright.jsp"/>
</div>





<jsp:include page="../includes/footer.jsp"/>
<script src="../../static/assets/plugins/treeTable/jquery.treeTable.min.js"></script>
<%--自定义模态框--%>
<sys:modal/>

<script>
    $(function () {
        $('#treeTable').treeTable({
            column:1,
            expandLevel:2
        });
    });
</script>

</body>
</html>