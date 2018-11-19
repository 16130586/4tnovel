<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="search-account u-centered">
    <form method="post">
        <div class="search-bar u-centered u-align-center u-2x row" style="padding: 1rem">
            <div class="col-sm-2">
                <select name="type" class="input">
                    <option value="user-name"><label>Tài khoản</label></option>
                    <option value="display-name"><label>Biệt hiệu</label></option>
                    <option value="email"><label>Mail</label></option>
                </select>
            </div>
            <div class="col-sm-8">
                <input id="input" class="input u-width--full u-2x" type="text" name="input" required>
            </div>
            <div class="col-sm-2">
                <button type="submit" class="btn btn-primary u-2x">Tìm kiếm</button><br>
            </div>
        </div>
    </form>
    <hr>
</div>
