<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Board register</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Board Read 
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
            	
                    <div class="form-group">
                        <label>Bno</label>
                        <input class="form-control" type="text" name="bno" value="${board.bno}">
                    </div>
                    <div class="form-group">
                        <label>Title</label>
                        <input class="form-control" type="text" name="title" value="${board.title}">
                    </div>
                    <div class="form-group">
                        <label>Writer</label>
                        <input class="form-control" type="text" name="writer" value="${board.writer}">
                    </div>
                    <div class="form-group">
                        <label>Title Area</label>
                        <textarea class="form-control" name="content">${board.content}</textarea>
                    </div>
                    <button data-oper="modify" class="btn btn-default">Modify</button>
                    <button data-oper="list" onclick="location.href='list'" class="btn btn-default">List</button>
            	</form>                
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>

<jsp:include page="../includes/footer.jsp"></jsp:include>