<%@include file="includes/header.jsp"%>
<%@include file="includes/header_html.jsp"%>
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
	<div class="page-header pull-left">
		<div class="page-title">Grados</div>
	</div>
	<div class="clearfix"></div>
</div>

<input type="hidden" name="elPath" id="elPath" value="${conPath}">

<div class="page-content">

	<table id="grados" class="table table-bordered table-striped">
		<thead>
			<tr>
				<th>#</th>
				<th>Grado</th>
				<th>Editar</th>
			</tr>
		</thead>

		<tfoot>
			<tr>
				<th>#</th>
				<th>Grado</th>
				<th>Editar</th>
			</tr>
		</tfoot>
	</table>

</div>

<%@include file="includes/footer.jsp"%>
<script src="${conPath}/public/lib/sae/js/gradoTable.js"></script>
<%@include file="includes/footer_bottom.jsp"%>