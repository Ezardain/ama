# User controller
class UsersController < ApplicationController
  before_action :authenticate_user!
  def new
    @user = User.new
  end

  def create
    @user = User.new(user_params)
    if @user.save
      flash[:success] = 'Usuario creado con éxito'
      redirect_to root_path
    else
      flash[:error] = @user.errors.full_messages
      render 'new'
    end
  end

  private

  def user_params
    params.require(:user).permit(:name, :color_id, :last_name, :password,
                                 :password_confirmation, :email)
  end
end
