<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${base}/resources/components/bootstrap/3.0.2/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${base }/resources/components/aceui/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="${base}/resources/components/jquery-ui/1.10.3/css/aceui/jquery-ui-1.10.3.full.min.css" />
<link rel="stylesheet"
	href="${base }/resources/components/aceui/css/datepicker.css" />
<link rel="stylesheet"
	href="${base }/resources/components/aceui/css/ui.jqgrid.css" />

<link rel="stylesheet"
	href="${base }/resources/components/aceui/css/ace.min.css" />
<link rel="stylesheet"
	href="${base }/resources/components/aceui/css/ace-rtl.min.css" />
<link rel="stylesheet"
	href="${base }/resources/components/aceui/css/ace-skins.min.css" />

<script src="${base}/resources/components/jquery/1.7.2/jquery.js"></script>
<script src="${base }/resources/components/aceui/js/ace-extra.min.js"></script>
</head>
<body>
	<form class="form-horizontal" role="form">
		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-1"> 标题: </label>

			<div class="col-sm-9">
				<input type="text" id="form-field-1" placeholder="Username"
					class="col-xs-10 col-sm-5" />
			</div>
		</div>

		<div class="space-4"></div>

		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-2">开始时间:</label>

			<div class="col-sm-9">
				<input type="password" id="form-field-2" placeholder="Password"
					class="col-xs-10 col-sm-5" /> <span
					class="help-inline col-xs-12 col-sm-7"> <span class="middle">Inline
						help text</span>
				</span>
			</div>
		</div>

		<div class="space-4"></div>

		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-input-readonly"> Readonly field </label>

			<div class="col-sm-9">
				<input readonly="" type="text" class="col-xs-10 col-sm-5"
					id="form-input-readonly" value="This text field is readonly!" /> <span
					class="help-inline col-xs-12 col-sm-7"> <label
					class="middle"> <input class="ace" type="checkbox"
						id="id-disable-check" /> <span class="lbl"> Disable it!</span>
				</label>
				</span>
			</div>
		</div>

		<div class="space-4"></div>

		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-4">Relative Sizing</label>

			<div class="col-sm-9">
				<input class="input-sm" type="text" id="form-field-4"
					placeholder=".input-sm" />
				<div class="space-2"></div>

				<div class="help-block" id="input-size-slider"></div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-5">Grid Sizing</label>

			<div class="col-sm-9">
				<div class="clearfix">
					<input class="col-xs-1" type="text" id="form-field-5"
						placeholder=".col-xs-1" />
				</div>

				<div class="space-2"></div>

				<div class="help-block" id="input-span-slider"></div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right">Input
				with Icon</label>

			<div class="col-sm-9">
				<span class="input-icon"> <input type="text"
					id="form-field-icon-1" /> <i class="icon-leaf blue"></i>
				</span> <span class="input-icon input-icon-right"> <input
					type="text" id="form-field-icon-2" /> <i class="icon-leaf green"></i>
				</span>
			</div>
		</div>

		<div class="space-4"></div>

		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-6">Tooltip and help button</label>

			<div class="col-sm-9">
				<input data-rel="tooltip" type="text" id="form-field-6"
					placeholder="Tooltip on hover" title="Hello Tooltip!"
					data-placement="bottom" /> <span class="help-button"
					data-rel="popover" data-trigger="hover" data-placement="left"
					data-content="More details." title="Popover on hover">?</span>
			</div>
		</div>

		<div class="space-4"></div>

		<div class="form-group">
			<label class="col-sm-3 control-label no-padding-right"
				for="form-field-tags">Tag input</label>

			<div class="col-sm-9">
				<input type="text" name="tags" id="form-field-tags"
					value="Tag Input Control" placeholder="Enter tags ..." />
			</div>
		</div>

		<div class="clearfix form-actions">
			<div class="col-md-offset-3 col-md-9">
				<button class="btn btn-info" type="button">
					<i class="icon-ok bigger-110"></i> Submit
				</button>

				&nbsp; &nbsp; &nbsp;
				<button class="btn" type="reset">
					<i class="icon-undo bigger-110"></i> Reset
				</button>
			</div>
		</div>
	</form>
</body>
</html>