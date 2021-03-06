# Groups controller
class GroupsController < ApplicationController
  before_action :authenticate_user!

  def index
    @groups = Group.all
    authorize Group
  end

  def new
    @group = Group.new
    authorize @group
  end

  def create
    @group = Group.new(group_params)
    authorize @group
    if @group.save
      flash[:notice] = 'Grupo creado con éxito'
      redirect_to groups_path
    else
      render 'new'
    end
  end

  def edit
    @group = Group.find(params[:id])
    authorize @group
  end

  def update
    @group = Group.find(params[:id])
    authorize @group
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
