# Groups controller
class GroupsController < ApplicationController
  before_action :authenticate_user!

  def index
    @groups = Group.all
  end

  def new
    @group = Group.new
  end

  def create
    @group = Group.new(group_params)
    if @group.save
      flash[:notice] = 'Grupo creado con éxito'
      redirect_to groups_path
    else
      render 'new'
    end
  end

  def edit
    @group = Group.find(params[:id])
  end

  def update
    @group = Group.find(params[:id])
    if @group.update_attributes(group_params)
      flash[:success] = 'Group updated successfully'
      redirect_to groups_path
    else
      render 'edit'
    end
  end

  private

  def group_params
    params.require(:group).permit(:name, :semester_id)
  end
end
