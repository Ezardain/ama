# User controller
class UsersController < ApplicationController
  before_action :authenticate_user!

  def index
    @users = User.all
  end

  def new
    @user = User.new
  end

  def create
    @user = User.new(user_params)
    if @user.save
      flash[:success] = 'Usuario creado con éxito'
      redirect_to users_admin_index_path
    else
      render 'new'
    end
  end

  def edit
    @user = User.find(params[:id])
  end

  def update
    @user = User.find(params[:id])
    if @user.update_attributes(user_params)
      flash[:success] = 'User updated successfully'
      redirect_to users_path
    else
      render 'edit'
    end
  end

  private

  def user_params
    params.require(:user).permit(:name, :color_id, :last_name, :password,
                                 :password_confirmation, :email)
  end
end
